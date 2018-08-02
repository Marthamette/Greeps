import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A Greep is an alien creature that likes to collect tomatoes.
 * 
 * @author Martha
 * @version 0.1
 */
public class Greep extends Creature
{
    // Remember: you cannot extend the Greep's memory. So:
    // no additional fields (other than final fields) allowed in this class!
    
    /**
     * Default constructor for testing purposes.
     */
    public Greep()
    {
        this(null);
    }

    
    /**
     * Create a Greep with its home space ship.
     */
    public Greep(Ship ship)
    {
        super(ship);
    }
    

    /**
     * Do what a greep's gotta do.
     */
    public void act()
    {
        super.act();   // do not delete! leave as first statement in act
     
       //start of with if their at the ship 
       if (atShip())
       {
           //if they have just returned to the ship they drop off their load
           if(carryingTomato())
           {
               dropTomato();
               turn(180); // just to make it a higfher chance that they return to pile
           }
           //coud have used else but this makes it more sencible
           if(!carryingTomato())
           {
               //if they are are not carrying a tomato and end up at en edge while at the space ship they turn and move
               if(atEdges())
               {
                   turn(44);
                   move();
               }
           }
           //at the endd they all move so they get out of the situation
           move();
       }
       //if that is not the case(else if because to really make sure that if thei are not at the ship)
       else if(!atShip())
       {
           //obviousley they allways check for food
           checkFood();
           //this is a question if a pile has been foung(look at check food method)
           if(getFlag(1)==true)
           {
             //indicator for finding food
             spit("purple");
             
             loadTomato();
           }
           //when they find indicator they load tomatos on others for higher productivity#
           //(coud have done it all in one if but this made me get more points)
           if(seePaint("purple"))
           {
           }
           //if the greep is carrying a tomato 
            if(carryingTomato())
            {
                //they first move away from pile to not stop there
                move();
                //if they are NOT at any block they turn twards home 
                if(!atEdges())
                {
                    turnHome();
                }
                //when they ARE at a block they turn and move to get around the blockadge
                //there is probably a way that saves time but this one worked for me(allthough itdoes take more time)
                if(atEdges())
                {
                  turn(45);
                  move();
                }
            }
            //if they are not carrying a tomato they move wildly until they find one
            //not productive but works
           else
           {
            if(atEdges())
            {
                turn(45); 
            }
            move();
           }
       }
    }

    public boolean atEdges()
    {
         if(atWater())
         {
             return true;
         }
         else if(atWorldEdge())
         {
             return true;
         }
         else
         {
            return false;
         }
    }
    
    public boolean riverInWay()
    {
        turnHome();
        if(atWater())
        {
         return true;
        }
        else
        {
            return false;
        }
    }
    

    
    /**
     * Is there any food here where we are? If so, try to load some!
     */
    public void checkFood()
    {
        setFlag(1,false);
        // check whether there's a tomato pile here
        TomatoPile tomatoes = (TomatoPile) getOneIntersectingObject(TomatoPile.class);
        if(tomatoes != null) {
            setFlag(1,true);
            loadTomato();
            // Note: this attempts to load a tomato onto *another* Greep. It won't
            // do anything if we are alone here.
        }
    }


    /**
     * This method specifies the name of the author (for display on the result board).
     */
    public static String getAuthorName()
    {
        return "Martha";  // write your name here!
    }


    /**
     * This method specifies the image we want displayed at any time. (No need 
     * to change this for the competition.)
     */
    public String getCurrentImage()
    {
        if(carryingTomato())
            return "greep-with-food.png";
        else
            return "greep.png";
    }
}