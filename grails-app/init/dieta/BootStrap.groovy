package dieta

import diet.Diet
import diet.FoodItem
import diet.FoodItemAllowed
import diet.FoodItemForbidden

class BootStrap {

    def init = { servletContext ->


        Diet d = new Diet()
        d.id = 1
        d.setName("Aria Stark")
        d.setStart_at(new Date())
        d.save flush:true, failOnError: true

        Diet d2 = new Diet()
        d2.id = 2
        d2.setName("Samsa Stark")
        d2.setStart_at(new Date())
        d2.save flush:true, failOnError: true

        FoodItemForbidden f1 = new FoodItemForbidden()
        f1.id = 1
        f1.setName("milk")
        f1.setRecommendation("Avoiding all lactose-contained products")
      //  f1.setDiet(d)
       // f1.save flush:true, failOnError: true
        d.addToFoodForbidden(f1)

        FoodItemAllowed f2 = new FoodItemAllowed()
        f2.id = 2
        f2.setName("fruit")
        f2.setRecommendation("No more than three pieces per day")
       // f2.setDiet(d)
        //f2.save flush:true, failOnError: true
        d.addToFoodAllowed(f2)

        FoodItemForbidden f3 = new FoodItemForbidden()
        f3.id = 3
        f3.setName("sugar")
        f3.setRecommendation("Avoiding industrial products with white sugar")
        //f3.save flush:true, failOnError: true
        d2.addToFoodForbidden(f3)

        FoodItemAllowed f4 = new FoodItemAllowed()
        f4.id = 4
        f4.setName("vegetables")
        f4.setRecommendation("Preferable cooked - not crude")
       // f4.save flush:true, failOnError: true
        d2.addToFoodAllowed(f4)

        List<Diet> dietaList = new ArrayList<Diet>();
        dietaList.add(d)
        dietaList.add(d2)

    }
    def destroy = {
    }
}
