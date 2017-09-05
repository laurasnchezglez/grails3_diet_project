package FoodItem

import diet.Diet
import diet.FoodItem
import grails.gorm.transactions.Transactional
import grails.rest.RestfulController

@Transactional(readOnly = false)
class FoodItemController extends RestfulController{

    FoodItemController() {
        super(FoodItem)
    }

    def index() {
        // List<Diet> dietaList = Diet.findAll()
        // render(view:"index", model: ["dietaList":dietaList])

    }

    def show( ){
        FoodItem foodItem= FoodItem.get(params.id)

        render(view:"show", model: ["foodItem":foodItem])
    }
}
