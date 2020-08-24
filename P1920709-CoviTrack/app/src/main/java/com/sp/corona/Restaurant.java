package com.sp.corona;
//ARRAY LLIST IS THE ONE ON TOP.STORES IN LOCAL VARIABLE MEANS INSIDE????YOU WANT TO STORE IT INSIDE
//YOUR OWN VARIABLE.
public class Restaurant {

    private String restaurantName = ""; //input from rest list
    private String restaurantAddress = "";
    private String restaurantTel = "";

    private String restaurantType = "";

    public String getName() {return restaurantName;}



    public void setName(String restaurantName) { this.restaurantName = restaurantName;}
// recieves the input text in model(GETTER) and then store.update in local variable this.(((((restaurantName)))))


//    public String getAddress() {return; }
    public void setAddress(String restaurantAddress){this.restaurantAddress=restaurantAddress;}

    public String getRestaurantTel() {//IF DONT HAVE THIS,THE METHOD IS NOT DECLARED AND HENCE YOU CANNOT GET  THE DATA.
        return restaurantTel;
    }
    // GETTER IS TO DEFINE THEE METHODS SO THAT LATTER ON YOU CAN ACCESS THIS STORED DATA(THIS.....) USUING THIS METHOD.
//SETTER IS FROM OUTSIDE STORE THE DATA HERE
    public void setTelephone(String restaurantTel) { this.restaurantTel = restaurantTel; }

    public String getRestaurantType() { return restaurantType; }

    public void setRestaurantType(String restaurantType) { this.restaurantType = restaurantType; }


    @Override


    public String toString() {
        return (getName());
    }//the only sys.out

}
//set variables is from outside(restaurant list)
/*getter is usually whereby you want access the variable.By this, it means that you either just make
calculation from same input data(method in properties) or usually just for sysout.Usually things you wanna sys out
*/


//this.blahblahblah information will be stored  as this and  the stringtostring thingy basically just sysout the name only