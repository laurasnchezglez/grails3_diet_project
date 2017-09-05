package diet

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/json/diet/$id"(controller: 'diet', action: 'getJson')
        
        "/"(controller:"diet")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
