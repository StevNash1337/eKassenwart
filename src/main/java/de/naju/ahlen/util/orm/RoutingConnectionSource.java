package de.naju.ahlen.util.orm;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Implementaion of {@link AbstractRoutingConnectionSource}.
 */
public class RoutingConnectionSource extends AbstractRoutingConnectionSource {
    @Override
    protected Object determineCurrentLookupKey() {
        if (CurrentProject.getCurrentProject() == null) {
            return null;
        }
        return CurrentProject.getCurrentProject().getName();
    }

    @Override
    public void addConnectionSource(Object key, ConnectionSource connectionSource) throws SQLException {
        for (Class<?> clazz : TableFactory.getModelClasses()) {
            TableUtils.createTableIfNotExists(connectionSource, clazz);
        }
        targetConnectionSources.put(key, connectionSource);
    }

    @Override
    public void removeConnectionSource(Object key) {
        targetConnectionSources.remove(key);
    }

    @Override
    public ConnectionSource getConnectionSource(Object key) {
        return targetConnectionSources.get(key);
    }


}
