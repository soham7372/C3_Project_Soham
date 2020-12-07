import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    //REFACTOR ALL THE REPEATED LINES OF CODE
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");
    Restaurant restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);

    public void addToMenu(){
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }


    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
        Restaurant output = service.findRestaurantByName("Amelie's cafe");

        assertEquals("Amelie's cafe",output.getName());
    }
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
        assertThrows(restaurantNotFoundException.class,()-> service.findRestaurantByName("Marriot"));

    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        addToMenu();

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        addToMenu();

        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        addToMenu();

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}