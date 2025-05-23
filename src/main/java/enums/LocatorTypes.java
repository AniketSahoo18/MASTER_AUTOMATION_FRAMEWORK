package enums;

public enum LocatorTypes {
    XPATH("xpath"),
    CSS("css"),
    ID("id"),
    CLASS_NAME("className"),
    LINK_TEXT("linkText"),
    NAME("name"),
    PARTIAL_LINK_TEXT("partialLinkText"),
    TAG_NAME("tagName");

    private final String typeName;

    LocatorTypes(String name) {
        this.typeName = name;
    }
}
