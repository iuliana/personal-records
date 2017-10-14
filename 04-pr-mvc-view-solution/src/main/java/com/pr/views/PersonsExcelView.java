package com.pr.views;

import com.pr.ents.Person;
import com.pr.util.DateFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by iuliana.cosmina on 3/20/15.
 */
@Component("persons/list.xls")
public class PersonsExcelView extends AbstractXlsView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Person> accounts = (List<Person>) model.get("persons");
		Sheet sheet = workbook.createSheet();
		for (short i = 0; i < accounts.size(); i++) {
			Person person = accounts.get(i);
			Row row = sheet.createRow(i);
			row.createCell(0).setCellValue(person.getFirstName());
			row.createCell(1).setCellValue(person.getLastName());
			DateFormatter df = new DateFormatter();
			row.createCell(2).setCellValue(df.print(person.getDateOfBirth(), LocaleContextHolder.getLocale()));
		}
	}
}
