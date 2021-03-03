package serviceimple.commentserviceimple;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import dao.commentdao.CommentDao;
import dao.commodityorderdao.CommodityOrderDao;
import po.Comment;
import po.CommodityOrder;
import po.Result;
import service.commentservice.CommentService;

@Service
@Transactional
public class CommentServiceImple implements CommentService {

	@Autowired
	private CommentDao commentDao;
	@Autowired
	private CommodityOrderDao commodityOrderDao;

	// 评价
	public Result comment(Comment comment, List<MultipartFile> uploadImageList, Integer CommodityOrderId) throws Exception {
		// 将图片存入磁盘
		ArrayList<String> imageNameList = new ArrayList<String>();
		for (MultipartFile image : uploadImageList) {
			String fileName = UUID.randomUUID() + ".jpg";
			String filePath = "D:/Picture/Taobao/comment/" + fileName;
			image.transferTo(new File(filePath));
			imageNameList.add(fileName);
		}
		// 将图片名集合的长度扩展至5
		for (int i = imageNameList.size(); i < 5; i++) {
			imageNameList.add(null);
		}
		comment.setCommentPictures(imageNameList);
		// 评价
		commentDao.addComment(comment);
		// 修改订单状态为已评价
		CommodityOrder commodityOrder = new CommodityOrder();
		commodityOrder.setId(CommodityOrderId);
		commodityOrder.setOrderStatus("已评价");
		commodityOrderDao.updateCommodityOrder(commodityOrder);
		return Result.success();
	}

	public Result findComment(Comment comment) throws Exception {
		ArrayList<Comment> commentList = commentDao.findComment(comment);
		if(commentList.size() < 1) {
			return Result.fail();
		}
		return Result.success(commentList);
	}

}
