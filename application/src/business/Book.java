package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import framework.item.FRItem;
import framework.item.FRLendableItem;
import framework.item.IPrototype;

public class Book extends Publication {
	private static final long serialVersionUID = 2007L;
	private String title;
	private String isbn;
	private long id;
	private boolean popularity;
	public boolean available;
	private int maxCheckoutLength;
	private List<Author> authors;
	
	public IPrototype frclone(){
		Book b = new Book();
		b.title = this.title;
		b.isbn = this.isbn;
		b.id = this.id;
		b.popularity = this.popularity;
		b.available = this.available;
		b.maxCheckoutLength = this.maxCheckoutLength;
		b.authors = new ArrayList();
		for(int i = 0; i < this.authors.size(); i++){
			Author a = this.authors.get(i);
			b.authors.add(a);
		}
		return b;
	}
	public boolean addCopy(int cid){
		LendableCopy c = new LendableCopy(this, ""+cid);
		this.copyList.add(c);
		this.save();
		return true;
	}
	public String getAuthorsName(){
		String ret = "";
		for(int i = 0; i < authors.size(); i++){
			Author a = authors.get(i);
			ret += a.getFullname();
			if(i != authors.size() - 1){
				ret += ",";
			}
		}
		return ret;
	}

	public Book() {
		authors = new ArrayList();
	}
	public String getISBN(){
		return isbn;
	}
	public void setISBN(String i){
		this.isbn = i;
	}
	public void addAuthor(Author a){
		if(a == null){
			System.out.println("a error");
			return;
		}
		if(authors == null){
			System.out.println("array error");
			return;
		}
		authors.add(a);
	}

	public static Book bookWithISBN(String s){
		Book b = null;
		b = (Book)Book.getInstance(Book.class.getName(), s);
		return b;

	}

	public LendableCopy checkoutCopy(){
		LendableCopy ret = (LendableCopy)rent();
		if(ret != null){
			ret.setAvailable(false);
			this.save();
		}
		return ret;
	}
	public void checkinCopy(LendableCopy copy){
		FRLendableItem p = null;
		Book nb = Book.bookWithISBN(this.isbn);
		List<FRLendableItem> list = nb.getCopyList();
		for(int i = 0; i < list.size(); i++){
			p = list.get(i);
			if(p.getKey() == copy.getKey()){
				p.setAvailable(true);
				nb.save();
				return ;
			}
		}
	}
	public void isAvailable(boolean b) {
		available = b;
	}
	public String getAuthor(){
		String ret = "";
		for(int i = 0; i < authors.size(); i++){
			Author a = authors.get(i);
			ret += a.getFullname();
		}
		return ret;
	}
	@Override
	public String toString() {
		return " isbn: " + isbn;
	}
	public boolean isPopularity() {
		return popularity;
	}
	public void setPopularity(boolean popularity) {
		this.popularity = popularity;
	}

	@Override
	public String getKey() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getMaxCheckoutLength() {
		return maxCheckoutLength;
	}

	public void setMaxCheckoutLength(int maxCheckoutLength) {
		this.maxCheckoutLength = maxCheckoutLength;
	}

}