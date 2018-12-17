package au.com.wipro.ebay.pages;

import au.com.wipro.ebay.actions.AndroidActions;
import au.com.wipro.ebay.beans.GeneralIdentifiers;
import au.com.wipro.ebay.beans.User;
import au.com.wipro.ebay.utils.PropertiesHelper;
import org.openqa.selenium.By;

public class Home {

    PropertiesHelper propertiesHelper = new PropertiesHelper();
    GeneralIdentifiers identifiers = propertiesHelper.getProperties("general.properties", GeneralIdentifiers.class);
    User user = propertiesHelper.getProperties("user.properties", User.class);
    AndroidActions actions;

    public Home(AndroidActions actions) {
        this.actions = actions;
    }

    public void openAppMenu() {
        actions.tap(By.id(identifiers.getBreadcrumb().getIcon()));
    }

    public void signIn() {
        actions.tap(By.id(identifiers.getBreadcrumb().getSignIn()));
        actions.input(0, user.getUsername());
        actions.input(1, user.getPassword());
    }
}
