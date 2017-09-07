package FoodItem

import diet.Diet
import diet.FoodItem
import diet.FoodItemAllowed
import grails.gorm.transactions.Transactional
import grails.rest.RestfulController

@Transactional(readOnly = false)
class FoodItemAllowedController extends RestfulController{

    FoodItemAllowedController() {
        super(FoodItem)
    }

    def index() {
         List<FoodItemAllowed> foodItemAllowedList = FoodItemAllowed.findAll()
         render(view:"index", model: ["foodItemAllowedList":foodItemAllowedList])

    }

    def show( ){
        FoodItem foodItem= FoodItem.get(params.id)

        render(view:"show", model: ["foodItemAllowed":foodItem])
    }

    def edit( ){
        FoodItem fi= FoodItem.get(params.id)

        render(view:"edit", model: ["foodItemAllowed":fi])
    }

    def create() {
        FoodItem fi= new FoodItem(params)

        render(view:"create", model: ["foodItemAllowed":fi])
    }

    @Transactional
    def update(FoodItemAllowed foodItemAllowed) {
        if (foodItemAllowed == null) {
            return
        }

        if (foodItemAllowed.hasErrors()) {
            return
        }

        foodItemAllowed.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: ["foodItemAllowed", foodItemAllowed.id])
                redirect foodItemAllowed
            }
            '*'{ respond foodItemAllowed, [status: OK] }
        }

    }

    @Transactional
    def save(FoodItemAllowed foodItemAllowed) {
        if (foodItemAllowed == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (foodItemAllowed.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond foodItemAllowed.errors, view:'create'
            return
        }

        foodItemAllowed.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message("foodItemAllowed", default: 'foodItemAllowed'), foodItemAllowed.id])
                redirect foodItemAllowed
            }
            '*' { respond foodItemAllowed, [status: CREATED] }
        }
    }
}
