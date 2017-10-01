package de.naju.ahlen.util.orm;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.misc.IOUtils;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract {@link ConnectionSource} implementation that routes {@link #getReadOnlyConnection(String)},
 * {@link #getReadWriteConnection(String)}, {@link #getSpecialConnection(String)}, and {@link #getDatabaseType()}
 * calls to one of various target {@link ConnectionSource}s based on lookup key.
 *
 * @author Lucas
 */
public abstract class AbstractRoutingConnectionSource implements ConnectionSource {

    private static Logger logger = LoggerFactory.getLogger(AbstractRoutingConnectionSource.class);

    protected Map<Object, ConnectionSource> targetConnectionSources = new HashMap<>();

    private ConnectionSource defaultConnectionSource;

    private boolean fallback = true;

    public void setTargetConnectionSources(Map<Object, ConnectionSource> targetConnectionSources) {
        this.targetConnectionSources = targetConnectionSources;
    }

    public void setDefaultConnectionSource(ConnectionSource defaultConnectionSource) {
        this.defaultConnectionSource = defaultConnectionSource;
    }

    public void setFallback(boolean fallback) {
        this.fallback = fallback;
    }

    @Override
    public void close() throws IOException {
        for (ConnectionSource connectionSource : targetConnectionSources.values()) {
            logger.debug("Closing ConnectionSource " + connectionSource.toString());
            connectionSource.close();
            logger.debug("Closed ConnectionSource " + connectionSource.toString());
        }
        logger.debug("Closing ConnectionSource " + defaultConnectionSource.toString());
        defaultConnectionSource.close();
        logger.debug("Closed ConnectionSource " + defaultConnectionSource.toString());
    }

    @Override
    public DatabaseConnection getReadOnlyConnection(String tableName) throws SQLException {
        return determineTargetConnectionSource().getReadOnlyConnection(tableName);
    }

    @Override
    public DatabaseConnection getReadWriteConnection(String tableName) throws SQLException {
        return determineTargetConnectionSource().getReadWriteConnection(tableName);
    }

    @Override
    public void releaseConnection(DatabaseConnection connection) throws SQLException {
        determineTargetConnectionSource().releaseConnection(connection);
    }

    @Override
    public boolean saveSpecialConnection(DatabaseConnection connection) throws SQLException {
        return determineTargetConnectionSource().saveSpecialConnection(connection);
    }

    @Override
    public void clearSpecialConnection(DatabaseConnection connection) {
        determineTargetConnectionSource().clearSpecialConnection(connection);
    }

    @Override
    public DatabaseConnection getSpecialConnection(String tableName) {
        return determineTargetConnectionSource().getSpecialConnection(tableName);
    }

    @Override
    public void closeQuietly() {
        for (ConnectionSource connectionSource : targetConnectionSources.values()) {
            logger.debug("Closing ConnectionSource quietly " + connectionSource.toString());
            IOUtils.closeQuietly(connectionSource);
            logger.debug("Closed ConnectionSource quietly " + connectionSource.toString());
        }
        logger.debug("Closing ConnectionSource quietly " + defaultConnectionSource.toString());
        IOUtils.closeQuietly(defaultConnectionSource);
        logger.debug("Closed ConnectionSource quietly " + defaultConnectionSource.toString());
        IOUtils.closeQuietly(this);
    }

    @Override
    public DatabaseType getDatabaseType() {
        return determineTargetConnectionSource().getDatabaseType();
    }

    @Override
    public boolean isOpen(String tableName) {
        return determineTargetConnectionSource().isOpen(tableName);
    }

    @Override
    public boolean isSingleConnection(String tableName) {
        return determineTargetConnectionSource().isSingleConnection(tableName);
    }

    protected ConnectionSource determineTargetConnectionSource() {
        Object lookupKey = determineCurrentLookupKey();
        ConnectionSource connectionSource = targetConnectionSources.get(lookupKey);
        if (connectionSource == null) {
            if (fallback) {
                return defaultConnectionSource;
            }
            throw new IllegalStateException("Cannot determine target ConnectionSource for lookup key " + lookupKey);
        }
        return connectionSource;
    }

    protected abstract Object determineCurrentLookupKey();

    public abstract void addConnectionSource(Object key, ConnectionSource connectionSource) throws SQLException;

    public abstract void removeConnectionSource(Object key);

    public abstract ConnectionSource getConnectionSource(Object key);
}
