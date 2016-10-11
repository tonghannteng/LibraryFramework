package business;

import framework.item.FRItem;

public class PublicationFacotry {
	public static FRItem createPublication(String type){
		if(type == "book"){
			return new Book();
		}else if(type == "magazine"){
			return new Magazin();
		}
		return null;
	}
}
