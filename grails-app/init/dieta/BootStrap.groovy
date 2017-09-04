package dieta

import diet.Diet

class BootStrap {

    def init = { servletContext ->


        Diet d = new Diet()
        d.setName("Aria Stark")
        d.setStart_at(new Date())

        d.save flush:true, failOnError: true

        Diet d2 = new Diet()
        d2.setName("Samsa Stark")
        d2.setStart_at(new Date())

        d2.save flush:true, failOnError: true

        List<Diet> dietaList = new ArrayList<Diet>();
        dietaList.add(d)
        dietaList.add(d2)
    }
    def destroy = {
    }
}
