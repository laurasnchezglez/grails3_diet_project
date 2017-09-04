package diet

import grails.rest.Resource

@Resource(uri='/json/dietas', formats=['json'])
class Diet {

    Integer id
    String name
    Date start_at

    Diet(){}

    static constraints = {
    }
}
