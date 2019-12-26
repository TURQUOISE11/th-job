package cn.lhemi.thjob.admin.common.constant;

/**
 * @author lengleng
 * @date 2017/10/29
 */
public interface CommonConstants {

	String BASE_STR = "abcdefghijklmnopqrstuvwxyzuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ./";
	/**
	 * header 中版本信息
	 */
	String VERSION = "VERSION";

	/**
	 * 删除
	 */
	String STATUS_DEL = "1";
	/**
	 * 正常
	 */
	String STATUS_NORMAL = "0";

	/**
	 * 锁定
	 */
	String STATUS_LOCK = "9";

	/**
	 * 菜单
	 */
	String MENU = "0";

	/**
	 * 菜单树根节点
	 */
	Integer MENU_TREE_ROOT_ID = -1;

	/**
	 * 编码
	 */
	String UTF8 = "UTF-8";

	/**
	 * 验证码前缀
	 */
	String DEFAULT_CODE_KEY = "DEFAULT_CODE_KEY_";

	/**
	 * 成功标记
	 */
	Integer SUCCESS = 0;
	/**
	 * 失败标记
	 */
	Integer FAIL = 1;

}
