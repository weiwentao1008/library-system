package com.library.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.web.bean.Book;
import com.library.web.dto.AddBookDTO;
import com.library.web.exception.WebException;
import com.library.web.service.imp.BookService;

@Controller
@RequestMapping(value = "/book")
public class BookController {
	@ResponseBody
	@RequestMapping(value = "/findbook", method = RequestMethod.GET)
	public Map<String,Object> ShowFindBook(@RequestParam("bookname") String bookname)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		//System.out.println(bookname);
		List<Book> bookList=BookService.bookQuery(bookname);
		if(bookList.size()<=0)
			throw new WebException("查询图书失败");
		else
		{
			modelMap.put("booklist",bookList);
		}
		return modelMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getBookInfo", method = RequestMethod.GET)
	public Map<String, Object> getbookinfo(@RequestParam("bookid") Integer bookid)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		Book a=BookService.getBookInfo(bookid);
		if(a!=null)
		{
			modelMap.put("bookinfo",a);
		} 
		else
			throw new WebException("获取图书信息失败");
		return modelMap;
		
	}
	@ResponseBody
	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public Map<String,Object> SaveBook(@RequestBody AddBookDTO addBookDTO)
	{	
		Map<String,Object> modelMap=new HashMap<String,Object>();
		Book a=new Book();
		a.setAuthor(addBookDTO.getAuthor());
		a.setCount(addBookDTO.getCount());
		a.setHascount(addBookDTO.getCount());
		a.setIsbn(addBookDTO.getIsbn());
		a.setName(addBookDTO.getBookname());
		a.setPoint(addBookDTO.getPoint());
		a.setPrice(addBookDTO.getPrice());
		a.setType(addBookDTO.getType());
		if(!BookService.bookAdd(a))
			throw new WebException("添加图书失败");
		return modelMap;
	}
	@ResponseBody
	@RequestMapping(value = "/changebook", method = RequestMethod.PUT)
	public Map<String,Object> changeBook(@RequestBody AddBookDTO changebook)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		Book a=BookService.findBookByIsbn(changebook.getIsbn());
		a.setAuthor(changebook.getAuthor());
		a.setCount(changebook.getCount());
		a.setIsbn(changebook.getIsbn());
		a.setName(changebook.getBookname());
		a.setPoint(changebook.getPoint());
		a.setPrice(changebook.getPrice());
		a.setType(changebook.getType());
		if(!BookService.bookChange(a))
			throw new WebException("修改图书失败");
		return modelMap;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/getbookinfoForchange", method = RequestMethod.GET)
	public Map<String,Object> getbookinfoForchange(@RequestParam("bookisbn") String isbn)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		Book a=BookService.findBookByIsbn(isbn);
		if(a==null)
			throw new WebException("获取用于修改的图书信息失败");
		modelMap.put("bookinfo",a);
		return modelMap;
	}
}
