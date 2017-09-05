package diet

import grails.rest.Resource

@Resource(formats=['json'])
class Diet {

    Integer id
    String name
    Date start_at

    static hasMany = [foodAllowed: FoodItemAllowed, foodForbidden: FoodItemForbidden]

    Diet(){}

    static constraints = {
        name maxSize: 150, blank: false, nullable: false
        start_at nullable: false
    }

    String toString() {
        "$name ($start_at)"
    }
}
