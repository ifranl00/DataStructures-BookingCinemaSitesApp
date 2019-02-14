package ule.edi.event;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ule.edi.model.Configuration.Type;
import ule.edi.model.*;

public class EventArrayImpl implements Event {
	
	
	private String name;
	private Date date;
	
	private Double priceGold;    // precio de entradas tipo GOLD
	private Double priceSilver;  // precio de entradas tipo SILVER
	
	private int nGold;    // Nº de butacas de tipo GOLD
	private int nSilver;  // Nº de butacas de tipo SILVER
	
	private Seat[] gold;
	private Seat[] silver;
	
	
	
   public Double getPriceGold() {
		return priceGold;
	}


	public void setPriceGold(Double priceGold) {
		this.priceGold = priceGold;
	}


	public Double getPriceSilver() {
		return priceSilver;
	}


	public void setPriceSilver(Double priceSilver) {
		this.priceSilver = priceSilver;
	}


public EventArrayImpl(String name, Date date, int nGold, int nSilver){
	   //TODO 
	   // utiliza los precios por defecto: DEFAULT_PRICE_GOLD y DEFAULT_PRICE_SILVER definidos en Configuration.java
	   this.name = name;
	   this.date = date;
	   this.nGold = nGold;
	   this.nSilver = nSilver;
	   this.priceGold = Configuration.DEFAULT_PRICE_GOLD;
	   this.priceSilver = Configuration.DEFAULT_PRICE_SILVER;
	   
	   // Debe crear los arrays de butacas gold y silver
	   this.gold = new Seat[nGold];
	   this.silver = new Seat[nSilver];
	   
   }
   
   
   public EventArrayImpl(String name, Date date, int nGold, int nSilver, Double priceGold, Double priceSilver){
	   //TODO 
	  
	   this.name = name;
	   this.date = date;
	   this.nGold = nGold;
	   this.nSilver = nSilver;
	   this.priceGold = priceGold;
	   this.priceSilver = priceSilver;
	   // Debe crear los arrays de butacas gold y silver
	   this.gold = new Seat[nGold];
	   this.silver = new Seat[nSilver];
	   
   }
   

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public Date getDate() {
		// TODO Auto-generated method stub
		return this.date;
	}

	
	@Override
	public int getNumberOfAttendingChildren() {
		// TODO Auto-generated method stub
		int NumberOfAttendingChildrenGold = 0;
		int NumberOfAttendingChildrenSilver = 0;
		int NumberOfAttendingChildren = 0;
		
		for(int i = 0; i < gold.length; i++) {
			if(gold[i].getHolder().getAge() < Configuration.CHILDREN_EXMAX_AGE) {
				NumberOfAttendingChildrenGold++;
			}
			
		}
		for(int j = 0; j < silver.length; j++) {
			if(gold[j].getHolder().getAge() < Configuration.CHILDREN_EXMAX_AGE) {
				NumberOfAttendingChildrenSilver++;
			}
		}
		
		NumberOfAttendingChildren = NumberOfAttendingChildrenGold + NumberOfAttendingChildrenSilver;
		return NumberOfAttendingChildren;
	}

	@Override
	public int getNumberOfAttendingAdults() {
		// TODO Auto-generated method stub
		int NumberOfAttendingAdultsGold = 0;
		int NumberOfAttendingAdultsSilver = 0;
		int NumberOfAttendingAdults = 0;
		
		for(int i = 0; i < gold.length; i++) {
			if((gold[i].getHolder().getAge() >= Configuration.CHILDREN_EXMAX_AGE) &&(gold[i].getHolder().getAge() < Configuration.ELDERLY_PERSON_INMIN_AGE)) {
				NumberOfAttendingAdultsGold++;
			}
			
		}
		for(int j = 0; j < silver.length; j++) {
			if((gold[j].getHolder().getAge() >= Configuration.CHILDREN_EXMAX_AGE) &&(gold[j].getHolder().getAge() < Configuration.ELDERLY_PERSON_INMIN_AGE)) {
				NumberOfAttendingAdultsSilver++;
			}
		}
		
		NumberOfAttendingAdults = NumberOfAttendingAdultsGold + NumberOfAttendingAdultsSilver;
		return NumberOfAttendingAdults;

	}

	@Override
	public int getNumberOfAttendingElderlyPeople() {
		// TODO Auto-generated method stub
		int NumberOfAttendingElderlyGold = 0;
		int NumberOfAttendingElderlySilver = 0;
		int NumberOfAttendingElderly = 0;
		
		for(int i = 0; i < gold.length; i++) {
			if((gold[i].getHolder().getAge() > Configuration.ELDERLY_PERSON_INMIN_AGE) && (gold[i].getHolder().getAge() < Integer.MAX_VALUE)) {
				NumberOfAttendingElderlyGold++;
			}
			
		}
		for(int j = 0; j < silver.length; j++) {
			if((silver[j].getHolder().getAge() > Configuration.ELDERLY_PERSON_INMIN_AGE) && (silver[j].getHolder().getAge() < Integer.MAX_VALUE)) {
				NumberOfAttendingElderlySilver++;
			}
		}
		
		NumberOfAttendingElderly = NumberOfAttendingElderlyGold + NumberOfAttendingElderlySilver;
		return NumberOfAttendingElderly;
	}

	@Override
	public int getNumberOfSoldSeats() {
		// TODO Auto-generated method stub
		int NumberOfSoldSeats = 0;
		NumberOfSoldSeats = getNumberOfSoldGoldSeats() + getNumberOfSoldSilverSeats();
		return NumberOfSoldSeats;
		
	}

	@Override
	public int getNumberOfSoldGoldSeats() {
		// TODO Auto-generated method stub
		int NumberOfSoldGoldSeats = 0;
		
		for(int i = 0; i < gold.length; i++) {
			if(gold[i] != null) {
				
				NumberOfSoldGoldSeats++;
			}
		}
		return NumberOfSoldGoldSeats;
	}

	@Override
	public int getNumberOfSoldSilverSeats() {
		// TODO Auto-generated method stub
		int NumberOfSoldSilverSeats = 0;
		
		for(int i = 0; i < silver.length; i++) {
			if(gold[i] != null) {
				
				NumberOfSoldSilverSeats++;
			}
		}
		return NumberOfSoldSilverSeats;
	}

	@Override
	public int getNumberOfSeats() {
		// TODO Auto-generated method stub
		int NumberOfSeats = 0;
		NumberOfSeats = getNumberOfGoldSeats() + getNumberOfSilverSeats();
		return NumberOfSeats;
	}

	@Override
	public int getNumberOfGoldSeats() {
		// TODO Auto-generated method stub
		int NumberOfGoldSeats = 0;
		NumberOfGoldSeats = gold.length;
		return NumberOfGoldSeats;
	}

	@Override
	public int getNumberOfSilverSeats() {
		// TODO Auto-generated method stub
		int NumberOfSilverSeats = 0;
		NumberOfSilverSeats = silver.length;
		return NumberOfSilverSeats;
	}


	@Override
	public int getNumberOfAvailableSeats() {
		// TODO Auto-generated method stub
		int NumberOfAvailableSeats = 0;
		int NumberOfAvailableSeatsGold = 0;
		int NumberOfAvailableSeatsSilver = 0;
		
		for(int i = 0; i < gold.length ; i++) {
			if(gold[i] == null) {
				NumberOfAvailableSeatsGold++;
			}
		}
		
		for(int j = 0; j < silver.length; j++) {
			if(silver[j] == null) {
				NumberOfAvailableSeatsSilver++;
			}
		}
		
		NumberOfAvailableSeats = NumberOfAvailableSeatsGold + NumberOfAvailableSeatsSilver;
		return NumberOfAvailableSeats;
	}


	@Override
	public Seat getSeat(int pos, Type type) {
		// TODO Auto-generated method stub
		Seat s1 = null;
		int i = 0;
		
		
		while((pos > 0 ) && ((type == Configuration.Type.SILVER) || (type == Configuration.Type.GOLD) )) {
			if(type == Configuration.Type.SILVER) {
				if(pos == silver[i].getPosition()) {
					s1 = silver[i];
				}

			}else {
				if(pos == gold[i].getPosition()) {
					s1 = gold[i];
				}
			}
			i++;
		}
		return s1;
	}


	@Override
	public Person refundSeat(int pos, Type type) {
		// TODO Auto-generated method stub
		Person p1 = null;
		int i = 0;
		Seat s1 = getSeat(pos,type);
		while((pos > 0 ) && ((type == Configuration.Type.SILVER) || (type == Configuration.Type.GOLD) )) {
			if(type == Configuration.Type.SILVER) {
				if(s1 == silver[i]) {
					p1 = silver[i].getHolder();
					silver[i] = null;
				}

			}else {
				if(s1 == gold[i]) {
					p1 = gold[i].getHolder();
					gold[i] = null;
				}
			}
			i++;
		}
			
		return p1;
	}


	@Override
	public boolean sellSeat(int pos, Person p, Type type) {
		// TODO Auto-generated method stub
		boolean isSeatSold = false;
		
		if(type == Configuration.Type.GOLD) {
			if(getNumberOfGoldSeats() > 0) {
				for(int i = 0; i < gold.length; i++) {
					if((pos == gold[i].getPosition()) && (gold[i] == null)) {
						
						gold[i] = new Seat(null,pos,type, p);
						isSeatSold = true;
						
					}
					
				}
				
			}
		}else {
			if(getNumberOfSilverSeats() > 0) {
				for(int j = 0; j < silver.length; j++) {
					if((pos == silver[j].getPosition()) && (silver[j] == null)) {
						
						silver[j] = new Seat(null,pos,type,p);
						isSeatSold = true;
					}
					
				}
			}
		}
		
		return isSeatSold;
	}


	@Override
	public List<Integer> getAvailableGoldSeatsList() {
		// TODO Auto-generated method stub
		List<Integer> AvailableGoldSeatsList = new ArrayList<Integer>();
		
		for(int i = 0; i < gold.length; i++) {
			
			if(gold[i] == null) {
				
			 AvailableGoldSeatsList.add(i);
				
			}
			
		}
		return AvailableGoldSeatsList;
	}


	@Override
	public List<Integer> getAvailableSilverSeatsList() {
		// TODO Auto-generated method stub
		List<Integer> AvailableSilverSeatsList = new ArrayList<Integer>();
		
		for(int i = 0; i < gold.length; i++) {
			
			if(gold[i] == null) {
				
			 AvailableSilverSeatsList.add(i);
				
			}
			
		}
		
		return AvailableSilverSeatsList;
	}


	@Override
	public Double getPrice(Seat seat) {
		// TODO Auto-generated method stub
		double price = 0;
		
		if ((seat.getType() == Configuration.Type.GOLD) || (seat.getType() == Configuration.Type.SILVER)) {
			if (seat.getType() == Configuration.Type.GOLD) {
					price = getPriceGold();
			}else {
					price = getPriceSilver();
			}
		}
		return price;
	}


	@Override
	public Double getCollectionEvent() {
		// TODO Auto-generated method stub
		double collectionEvent = 0;
		
		collectionEvent = (getNumberOfSoldGoldSeats() * getPriceGold()) + (getNumberOfSoldSilverSeats() * getPriceSilver());
		
		return collectionEvent;
	}


	@Override
	public int getPosPersonGold(Person p) {
		// TODO Auto-generated method stub
		int posPersonGold = -1;
		int i = 0;
		while (p != null ) {
			if(p == gold[i].getHolder()) {
				
				posPersonGold = gold[i].getPosition();
			}
			i++;
		}
		
		return posPersonGold;
	}


	@Override
	public int getPosPersonSilver(Person p) {
		// TODO Auto-generated method stub
		int posPersonSilver = -1;
		int i = 0;
		while (p != null ) {
			if(p == silver[i].getHolder()) {
				
				posPersonSilver = silver[i].getPosition();
			}
			i++;
		}
		
		return posPersonSilver;
	}


	@Override
	public boolean isGold(Person p) {
		// TODO Auto-generated method stub
		boolean isGold = false;
		int i = 0;
		while (p != null ) {
			if(p == gold[i].getHolder()) {
				
				isGold = true;
			}else 
			i++;
		}
		
		return isGold;
	}


	@Override
	public boolean isSilver(Person p) {
		// TODO Auto-generated method stub
		boolean isSilver = false;
		int i = 0;
		while (p != null ) {
			if(p == silver[i].getHolder()) {
				
				isSilver = true;
			}else 
			i++;
		}
		
		return isSilver;
	}

	
}
