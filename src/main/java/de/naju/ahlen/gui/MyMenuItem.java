package de.naju.ahlen.gui;

import de.naju.ahlen.gui.extensions.MySideMenu;
import de.naju.ahlen.gui.extensions.MySideMenu.MenuRegistration;
import de.naju.ahlen.gui.extensions.MySideMenu.MenuClickHandler;

import java.util.LinkedList;
import java.util.List;

public class MyMenuItem {

    private static final int INDENT = 4;

    private MainWindowController mainWindowController;
    private MySideMenu sideMenu;

    private String caption;
    private MenuClickHandler registrationHandler;
    private MenuRegistration menuRegistration;

    private MyMenuItem parent;
    private List<MyMenuItem> children;
    private boolean visible;

    public MyMenuItem(MainWindowController mainWindowController, String caption, MenuClickHandler registrationHandler) {
        this.mainWindowController = mainWindowController;
        this.sideMenu = mainWindowController.mySideMenu();
        this.caption = caption;
        this.registrationHandler = registrationHandler;
        this.children = new LinkedList<>();
        visible = true;
        //show();
    }

    public MyMenuItem getParent() {
        return parent;
    }

    public void setParent(MyMenuItem parent) {
        this.parent = parent;
    }

    public List<MyMenuItem> getChildren() {
        return children;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void addChild(MyMenuItem child) {
        children.add(child);
        child.setParent(this);
    }

    public void show() {
        int depth = depth(this);
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < INDENT * depth; i++) {
            stringBuilder.append("\u00A0");
        }
        stringBuilder.append(caption);
        menuRegistration = sideMenu.addMenuItem(stringBuilder.toString(), registrationHandler);
    }

    public void hide() {
        if (menuRegistration != null) {
            menuRegistration.remove();
        }
    }

    public void toggleVisible() {
        visible = !visible;
    }


    public void toggleChildren() {
        for (MyMenuItem child : children) {
            child.toggleVisible();
        }
        mainWindowController.refreshMenu();
    }

    private int depth(MyMenuItem item) {
        if (item.getParent() == null) {
            return 0;
        } else {
            return depth(item.getParent()) + 1;
        }
    }
}
