package FoodItem

import diet.FoodItem
import diet.FoodItemAllowed
import diet.FoodItemForbidden
import grails.gorm.transactions.Transactional
import grails.rest.RestfulController

@Transactional(readOnly = false)
class FoodItemForbiddenController extends RestfulController{

    FoodItemForbiddenController() {
        super(FoodItem)
    }

    def index() {
        List<FoodItemForbidden> foodItemForbiddenList = FoodItemForbidden.findAll()
        render(view:"index", model: ["foodItemForbiddenList":foodItemForbiddenList])

    }

    def show( ){
        FoodItemForbidden foodItem= FoodItemForbidden.get(params.id)

        render(view:"show", model: ["foodItemForbidden":foodItem])
    }

    def edit( ){
        FoodItem fi= FoodItem.get(params.id)

        render(view:"edit", model: ["foodItemForbidden":fi])
    }

    def create() {
        FoodItem fi= new FoodItem(params)

        render(view:"create", model: ["foodItemForbidden":fi])
    }

    @Transactional
    def update(FoodItemForbidden foodItemForbidden) {
        if (foodItemForbidden == null) {
            return
        }

        if (foodItemForbidden.hasErrors()) {
            return
        }

        foodItemForbidden.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: ["foodItemForbidden", foodItemForbidden.id])
                redirect foodItemForbidden
            }
            '*'{ respond foodItemForbidden, [status: OK] }
        }

    }

    @Transactional
    def save(FoodItemForbidden foodItemForbidden) {
        if (foodItemForbidden == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (foodItemForbidden.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond foodItemForbidden.errors, view:'create'
            return
        }

        foodItemForbidden.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message("foodItemForbidden", default: 'foodItemForbidden'), foodItemForbidden.id])
                redirect foodItemForbidden
            }
            '*' { respond foodItemForbidden, [status: CREATED] }
        }
    }
}
