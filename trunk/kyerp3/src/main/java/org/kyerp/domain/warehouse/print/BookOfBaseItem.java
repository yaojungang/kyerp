package org.kyerp.domain.warehouse.print;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.kyerp.domain.warehouse.BaseItem;

/**
 * 库存图书类
 *org.kyerp.domain.warehouse.print.BookOfBaseItem.java
 * 
 * @author y109
 *         2010-4-4下午09:11:55
 */
@Entity
@DiscriminatorValue("book")
public class BookOfBaseItem extends BaseItem implements Serializable{
	private static final long	serialVersionUID	= 1L;
	/** 书名 */
	private String				bookName;
	/** ISBN */
	private String				isbn;

	/** 出版社 */
	private String				press;

	/** 定价 */
	private String				bookPrice;

	/** 版次 */
	private String				editionNo;

	/** 字数 */
	private String				wordCount;

	/** 页数 */
	private String				pageCount;

	/** 开本 */
	private String				bookSize;

	/** 包装 */
	private String				pack;

	/** 封面图片 */
	private String				imageUrl;

	/** 内容简介 */
	private String				description;

	/** 作者简介 */
	private String				authorDesc;

	public BookOfBaseItem() {
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public String getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(String bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getEditionNo() {
		return editionNo;
	}

	public void setEditionNo(String editionNo) {
		this.editionNo = editionNo;
	}

	public String getWordCount() {
		return wordCount;
	}

	public void setWordCount(String wordCount) {
		this.wordCount = wordCount;
	}

	public String getPageCount() {
		return pageCount;
	}

	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}

	public String getBookSize() {
		return bookSize;
	}

	public void setBookSize(String bookSize) {
		this.bookSize = bookSize;
	}

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthorDesc() {
		return authorDesc;
	}

	public void setAuthorDesc(String authorDesc) {
		this.authorDesc = authorDesc;
	}
}
