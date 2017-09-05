package diet

class FoodItem {

    Integer id
    String name
    String recommendation

    static belongsTo = [diet:Diet]

    static constraints = {
        name maxSize: 150, blank: false, nullable: false
        name maxSize: 1024, blank: true, nullable: true
    }

    String toString() {
        "$name ($recommendation)"
    }
}

class FoodItemAllowed extends FoodItem{

}

class FoodItemForbidden extends FoodItem{

}
