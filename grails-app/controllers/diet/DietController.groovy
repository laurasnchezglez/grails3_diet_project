package diet

import grails.gorm.transactions.Transactional
import grails.rest.RestfulController

@Transactional(readOnly = false)
class DietController extends RestfulController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    DietController() {
        super(Diet)
    }

    def index() { 
		//render "esta es mi diet!"

		List<Diet> dietaList = Diet.findAll()

		render(view:"index", model: ["dietaList":dietaList])
	}

	def show( ){
        Diet dieta= Diet.get(params.id)

        render(view:"show", model: ["diet":dieta])
	}

	def edit( ){
        Diet dieta= Diet.get(params.id)

        render(view:"edit", model: ["diet":dieta])
	}

    def create() {
        Diet dieta = new Diet(params)

        render(view:"create", model: ["diet":dieta])
    }

    @Transactional
    def update(Diet dieta) {
        if (dieta == null) {
            return
        }

        if (dieta.hasErrors()) {
            return
        }

        dieta.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: ["Diet", dieta.id])
                redirect dieta
            }
            '*'{ respond dieta, [status: OK] }
        }

    }

    @Transactional
    def save(Diet dieta) {
        if (dieta == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dieta.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dieta.errors, view:'create'
            return
        }

        dieta.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message("Diet", default: 'Diet'), dieta.id])
                redirect dieta
            }
            '*' { respond dieta, [status: CREATED] }
        }
    }


    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message("Diet", default: 'Diet'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def getJson(){
        println "EStamos en la fución getjson"
        Diet d = Diet.get(params.id)
        respond d
    }


}
