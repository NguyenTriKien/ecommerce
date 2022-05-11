package com.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;

import com.ecommerce.entity.Producer;

import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;

public class PaginationResult<E> {

	private int totalRecords;

	private int currentPage;

	private List<E> list;

	private int maxResult;

	private int totalPages;

	private int maxNavigationPages;

	private List<Integer> navigationPages;
	
	private List<Producer> producers;

	public List<Producer> getProducers() {
		return producers;
	}

	public void setProducers(List<Producer> producers) {
		this.producers = producers;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getMaxNavigationPages() {
		return maxNavigationPages;
	}

	public void setMaxNavigationPages(int maxNavigationPages) {
		this.maxNavigationPages = maxNavigationPages;
	}

	public List<Integer> getNavigationPages() {
		return navigationPages;
	}

	public void setNavigationPages(List<Integer> navigationPages) {
		this.navigationPages = navigationPages;
	}
	//-1 -->0 
	public PaginationResult(Query query, int page, int maxResult, int maxNavigationPage) {
		int pageIndex = page - 1 < 0 ? 0 : page - 1; // điều kiện dùng để xác định số trang bắt đầu, nếu page - 1 
		//mà < hơn 0 thì trả về 0 còn nếu ko thì trả về page - 1
		int fromRecordIndex = pageIndex * maxResult;// vị trí bắt đầu đếm số lượng trong trang
		int maxRecordIndex = fromRecordIndex + maxResult;// vị trí kết đếm số lượng trong trang

		ScrollableResults resultsScroll = query.scroll(ScrollMode.SCROLL_INSENSITIVE);

		List<E> results = new ArrayList<E>();// dùng để chứa danh sách sản phẩm 

		boolean hasResult = resultsScroll.first(); // biến để xác định xem là có phần tử trong list có 
		// phẩn tử hay ko
		if (hasResult) {
			hasResult = resultsScroll.scroll(fromRecordIndex);// nếu có phần tử lấy phần tử đấy ra 
			if (hasResult) {
				
				do {
					E record = (E) resultsScroll.get(0);// record dùng để chứa số lượng sản phẩm trong một trang
					results.add(record);// add record vô danh sách sản phẩm
				} while (resultsScroll.next() && resultsScroll.getRowNumber() >= fromRecordIndex
						&& resultsScroll.getRowNumber() < maxRecordIndex);
				  // kiểm tra xem có phẩn tử tiếp theo trong record hay ko, nếu phẩn tử đó >= fromRecordIndex
				// và nhỏ hơn maxRecordIndex thì tiếp tục vòng lăp đểm record trong "result", nếu ko thì thoát 
				//vòng lặp và lấy phần tử cuối cùng.
			}
			resultsScroll.last();
		}

		this.totalRecords = resultsScroll.getRowNumber() + 1;// tổng số lượng record mà web lấy được trong list
		this.currentPage = pageIndex + 1;// trang hiện tại
		this.list = results; // danh sách sản phẩm
		this.maxResult = maxResult;// số lượng sản phẩm một trang có thể hiện thị

		this.totalPages = (this.totalRecords / this.maxResult) + 1;// nếu trường hợp record trong một trang 
		// vượt quá số lượng cho phép, thì record dư ra sẽ được hiển thị trong trang mới
		this.maxNavigationPages = maxNavigationPage;// số lượng trang website được phép tạo

		if (maxNavigationPage < this.totalPages) {
			this.maxNavigationPages = maxNavigationPage;
		}
		this.calcNavigationPages();
	}

	private void calcNavigationPages() {

		this.navigationPages = new ArrayList<Integer>();// tạo danh sách số trang

		int current = this.currentPage > this.totalPages ? this.totalPages : this.currentPage;
		// hiển thị số trang hiện tại, nếu trang hiện tại lớn hơn tổng số trang thì trả về tổng số trang nếu ko thì
		// trả về trang hiện tại

		int begin = current - this.maxNavigationPages / 2;
		int end = current + this.maxNavigationPages / 2;

		this.navigationPages.add(1);
		if (begin > 2) {
			this.navigationPages.add(-1);// nếu trang bắt đầu lớn hơn 2 thì tạo ....
		}
		for (int i = begin; i < end; i++) {
			if (i > 1 && i < this.totalPages) {
				this.navigationPages.add(i);
			}
			// vòng lặp này mục đích là để add trang vào danh sách trang
		}
		if (end < this.totalPages - 2) {
			this.navigationPages.add(-1);
		}
		this.navigationPages.add(this.totalPages);
	}
}
