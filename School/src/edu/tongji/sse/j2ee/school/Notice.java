package edu.tongji.sse.j2ee.school;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.List;

import javax.sql.RowSet;

public class Notice {
	public final int id;
	Notice(int id) throws Exception {
		RowSet rs = DB.select("*", "notice", "notice_id = "+id);
		if (rs.next())
			this.id = id;
		else
			throw new Exception("NoticeNotFound");
	}
	
	public String getTitle() throws Exception {
		RowSet rs = DB.select("title", "notice", "notice_id = "+this.id);
		if (rs.next())
			return rs.getString("title");
		else 
			throw new Exception("NoticeTitleNotFound");
	}
	public String getHref() throws Exception {
		RowSet rs = DB.select("href", "notice", "notice_id = "+this.id);
		if (rs.next())
			return rs.getString("href");
		else 
			throw new Exception("NoticeHrefNotFound");
	}
	public void setTitle(String title) throws Exception {
		DB.setPara("notice", "title", title, "notice_id = "+this.id);
	}
	public void setHref(String href) throws Exception {
		DB.setPara("notice", "href", href, "notice_id = "+this.id);
	}
	
	// static
	public int getNewId() throws Exception {
		RowSet rs = DB.select("notice_id", "notice");
		int newId = 1;
		while (rs.next()) {
			if (rs.getInt("id") >= newId) {
				newId = rs.getInt("id")+1;
			}
		}
		return newId;
	}
	
	public Notice addNotice(int id, String title, String href) throws Exception {
		Connection conn = DB.getConnection();
		if (DB.select("*", "notice", "notice_id = "+id).next())
			throw new Exception("IdExisted");
		PreparedStatement pStmt = conn.prepareStatement("insert into notice(id,title,href) values(?,?,?)");
		pStmt.setInt(1, id);
		pStmt.setString(2, title);
		pStmt.setString(3, href);
		pStmt.executeUpdate();
		conn.close();
		return new Notice(id);
	}
	
	public Notice getNotice(int id) throws Exception {
		return new Notice(id);
	}
	
	public List<Notice> getNotices() throws Exception {
		List<Notice> notices = new LinkedList<Notice>();
		RowSet rs = DB.select("notice_id", "notice");
		while (rs.next()) {
			notices.add(new Notice(rs.getInt("notice_id")));
		}
		return notices;
	}
	
	public void removeNotice(Notice notice) throws Exception {
		DB.delete("notice", "notice_id = "+notice.id);
	}
	
}
