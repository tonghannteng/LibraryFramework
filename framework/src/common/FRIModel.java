package common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import database.FRDBFacade;
import framework.rentrecord.FRRentRecordEntry;

public abstract class FRIModel implements Serializable,FRIModelAPI{
	private FRIModelAPIImpl modelapi = new FRIModelAPIImpl();
	public abstract String getKey();
	public void init(){
		
	}
	public  void setKey(String k){
		
	}
	public void save(FRIModel o){
		modelapi.save(o);
	}
	public  void save(){
		save(this);
	}
	
	public  static FRIModel getInstance(String dir, String k){
		FRIModel ret =  (FRIModel) FRDBFacade.readObject(dir, k);
		if(ret != null){
			ret.init();
		}
		return ret;
	}
	public static List<FRIModel> getAllInstances(String dir){
		return (List<FRIModel>) FRDBFacade.readAllFiles(dir);
	}
}
