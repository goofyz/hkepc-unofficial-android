package net.louislam.hkepc.page;

import net.louislam.hkepc.Helper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ForumDisplay extends Page {

	public String getId() {
		return "forumdisplay";
	}

	public String getContent(Document doc) {
		StringBuilder sb = new StringBuilder();
		Element item;
		Element author;
		Element date;
		Element num;
		sb.append("<ul>");
		
		// Nav
		Helper.appendNav(sb, doc);
		
		// Sub Forum
		Elements subForums = doc.select("table[summary=subform] h2 a");
		if (subForums.size() >= 1) {
			sb.append(Helper.listViewDivider("�l����"));
		}
		
		for (Element sub : subForums) {
			sb.append("<li>" + sub + "</li>");
		}
		
		// Post List
		Elements groups = doc.select(".datatable tbody");

		for (Element g : groups) {
			item = g.select(".subject span a").first();
			
			if (item != null) {
				author = g.select(".author a").first();
				date = g.select(".author em").first();
				num = g.select(".nums").first();
				sb.append("<li>");
				
				sb.append(item.html(item.html() + 
						"<br /><span class=\"box\">" + author.html() + "</span> " +
						"<span class=\"box\">" + date + "</span> " +
						"<span class=\"box\">" + num + "</span> "));
				
				sb.append("</li>");
			} else
				sb.append(Helper.listViewDivider(""));
		}
		
		// Paging
		Helper.appendPaging(sb, doc);
		sb.append(Helper.clear());
		
		sb.append("</ul>");
			
		return sb.toString();
	}


}
