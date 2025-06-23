package enums;

public enum ProjectBrowser {
    Firefox("firefox"),
    Chrome("chrome"),
    Edge("edge"),
    Internet_explorer("ie");

    private final String browserName;

    ProjectBrowser(String browserName) {
        this.browserName = browserName;
    }

    public String getBrowserName() {
        return browserName;
    }

    public static ProjectBrowser fromString(String text) {
        for (ProjectBrowser browser : ProjectBrowser.values()) {
            if (browser.browserName.equalsIgnoreCase(text)) {
                return browser;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}
