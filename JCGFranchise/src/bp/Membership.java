/* JCG Franchise Management System
 * CIST 2931 Summer Session 2013 GTC
 * Author: #Mahmoodur
 * Name: Membership
 * Description: Describes a membership 
 */
package bp;


public class Membership {
    
    //attributes - DB
        private String memberID;
	private String discount;
	private String minAmount;
        
        //CONTRUCTORS
   public Membership(){
    
	memberID = "";
	discount = "";
	minAmount = "";
   }
    
   //3 arguments
   public Membership (String memberID, String discount, String minAmount){
       
	setMemberID(memberID);
	setDiscount(discount);
	setMinAmount(minAmount);
	      
   }
   
    //SETS AND GETS methods
    //attribute 1
    public void setMemberID (String memberID){       
       this.memberID = memberID;
    }
   
    public String getMemberID (){
       return memberID;
    }
    
    //attribute 2
    public void setDiscount (String discount){       
       this.discount = discount;
    }
   
    public String getDiscount (){
       return discount;
    }

    //attribute 3
   
     public void setMinAmount (String minAmount){       
       this.minAmount = minAmount;
    }
   public void setMinAmount (int minAmount){       
       this.minAmount = String.valueOf(minAmount);
   }
       
    public int getMinAmount (){
       return Integer.parseInt(minAmount);
    }
//UTILITIES
  @Override
   public String toString(){
    
    return String.format("%s%s  %s%s/n %s%s\n",
        "MemberID: ", getMemberID(), 
        "Discount: ", getDiscount(),
	"Min Amount: ", getMinAmount() );
	
    }//end UTILS  
  
} // end class Membership
