package service.commentservice;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import po.Comment;
import po.Result;

public interface CommentService {

	public Result comment(Comment comment, List<MultipartFile> uploadImageList, Integer CommodityOrderId) throws Exception;
	public Result findComment(Comment comment) throws Exception;
}
