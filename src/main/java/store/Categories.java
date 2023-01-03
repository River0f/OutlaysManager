package store;

public enum Categories {
    HOME("home"), FOOD("food"), TRAVEL("travel"), OTHER("other");

    private String categoryName;
    private Categories(String categoryName) {
        this.categoryName = categoryName;
    }
}
