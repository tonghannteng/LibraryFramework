package framework.item;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import common.FRIModel;
import database.FRDBFacade;

public abstract class FRItem extends FRIModel implements IPrototype{

	private static final long serialVersionUID = -5907220085112635152L;
	protected long itemId;
	public abstract String getKey();
	protected String name;
	protected String company;
	protected String year;
	protected Image pictures;
	protected boolean popularity;
	protected boolean available;
	protected List<FRLendableItem> copyList;
	
	public int getAvailableNumber(){
		int ret = 0;
		for(int i = 0; i < copyList.size(); i++){
			FRLendableItem li = copyList.get(i);
			if(li.isAvailable()){
				ret++;
			}
		}
		return ret;
	}
	public FRItem(){
		setCopyList(new ArrayList<FRLendableItem>());
	}
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPublishCompany() {
		return company;
	}
	public void setPublishCompany(String publishCompany) {
		this.company = publishCompany;
	}
	public String getPublishYear() {
		return year;
	}
	public void setPublishYear(String publishYear) {
		this.year = publishYear;
	}
	public Image getPictures() {
		return pictures;
	}
	public void setPictures(Image pictures) {
		this.pictures = pictures;
	}
	public boolean isPopularity() {
		return popularity;
	}
	public void setPopularity(boolean popularity) {
		this.popularity = popularity;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public int numberOfCopies(){
		return copyList.size();
	}
	public boolean hasLendableCopy(){
		for(int i = 0; i < copyList.size(); i++){
			FRLendableItem c = copyList.get(i);
			if(c.isAvailable()){
				return true;
			}
		}
		return false;
	}
	public FRLendableItem rent(){
		FRLendableItem ret = null;
		for(int i = 0; i < copyList.size(); i++){
			ret = copyList.get(i);
			if(ret.isAvailable()){
				return ret;
			}
		}
		return null;
	}
	public List<FRLendableItem> getCopyList() {
		return copyList;
	}
	public void setCopyList(List<FRLendableItem> copyList) {
		this.copyList = copyList;
	}
}
	