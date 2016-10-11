package common;

import java.io.Serializable;

import database.FRDBFacade;

public class FRIModelAPIImpl implements FRIModelAPI,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2499816687977174146L;

	public void save(FRIModel o) {
		FRDBFacade.saveObject(o);
	}

}
