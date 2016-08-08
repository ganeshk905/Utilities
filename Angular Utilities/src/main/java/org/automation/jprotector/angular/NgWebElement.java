package org.automation.jprotector.angular;


import org.automation.jprotector.JavaScriptBy;
import org.automation.jprotector.scripts.Evaluate;
import org.openqa.selenium.*;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Angular Web Element.=> i change implementation
 */
//public final class NgWebElement implements WebElement, WrapsElement
    // only thing added are the wait.. no more extra
public final class NgWebElement extends RemoteWebElement implements WrapsElement
{

    private final transient NgWebDriver driver;
    private final transient WebElement element;
    public NgWebElement(final NgWebDriver drv, final WebElement elm) {
        this.driver = drv;
        this.element = elm;
    }

    @Override
    public WebElement getWrappedElement() {
        return this.element;
    }

    @Override
    public void clear() {
        this.driver.waitForAngular();
        this.element.clear();
    }

    @Override
    public void click() {
        this.driver.waitForAngular();
        this.element.click();
    }

    public Object evaluate(final String expression) {
        this.driver.waitForAngular();
        final JavascriptExecutor executor = (JavascriptExecutor) this.driver
            .getWrappedDriver();
        return executor.executeScript(
            new Evaluate().content(),
            this.element,
            expression
        );
    }

    @Override
    public NgWebElement findElement(By by) {
        if (by instanceof JavaScriptBy) {
            ((JavaScriptBy) by).root = this.element;
        }
        this.driver.waitForAngular();
        return new NgWebElement(this.driver, this.element.findElement(by));
    }

    public List<NgWebElement> findNgElements(By by) {
        final List<WebElement> temp = findElements(by);
        final List<NgWebElement> elements = new ArrayList<>();
        for (final WebElement element : temp) {
            elements.add(new NgWebElement(this.driver, element));
        }
        return elements;
    }

    public List<WebElement> findElements(By by) {
        if (by instanceof JavaScriptBy) {
            ((JavaScriptBy) by).root = this.element;
        }
        final List<WebElement> returnElements = this.element.findElements(by);
        this.driver.waitForAngular();
        return returnElements;
    }

    public String getAttribute(String arg0) {
        this.driver.waitForAngular();
        return this.element.getAttribute(arg0);
    }

    public String getCssValue(String arg0) {
        this.driver.waitForAngular();
        return this.element.getCssValue(arg0);
    }

    public Point getLocation() {
        this.driver.waitForAngular();
        return this.element.getLocation();
    }

    public Dimension getSize() {
        this.driver.waitForAngular();
        return this.element.getSize();
    }


    @Override
    public Rectangle getRect() {
        return super.getRect();
    }

    public String getTagName() {
        this.driver.waitForAngular();
        return this.element.getTagName();
    }

    public String getText() {
        this.driver.waitForAngular();
        return this.element.getText();
    }

    public boolean isDisplayed() {
        this.driver.waitForAngular();
        return this.element.isDisplayed();
    }

    public boolean isEnabled() {
        this.driver.waitForAngular();
        return this.element.isEnabled();
    }

    public boolean isSelected() {
        this.driver.waitForAngular();
        return this.element.isSelected();
    }

    public void sendKeys(CharSequence... arg0) {
        this.driver.waitForAngular();
        this.element.sendKeys(arg0);

    }

    public void submit() {
        this.driver.waitForAngular();
        this.element.submit();
    }
/**
 * TOdo => a shot intregration
 */
    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return super.getScreenshotAs(target);
    }
}
