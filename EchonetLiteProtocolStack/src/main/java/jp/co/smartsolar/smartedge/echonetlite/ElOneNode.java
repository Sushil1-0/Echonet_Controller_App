package jp.co.smartsolar.smartedge.echonetlite;

import java.util.ArrayList;
import java.util.List;

import jp.co.smartsolar.smartedge.echonetlite.ElProcess.ElEventListener;
import jp.co.smartsolar.smartedge.echonetlite.devices.DeviceObjectSuperClass;
import jp.co.smartsolar.smartedge.echonetlite.profile.NodeProfileClass;

public class ElOneNode {

	/**
	 * ログ出力用タグ
	 */
	@SuppressWarnings("unused")
	private static final String TAG = "ElOneNode";

	/**
	 * 所属するELプロセスオブジェクト
	 */
	private ElProcess elProcess;

	/**
	 * ノードに固有のIPアドレス
	 */
	private String ipAddress;

	/**
	 * ノードプロファイルクラスオブジェクト
	 */
	private NodeProfileClass nodeProfile;

	/**
	 * デバイスEOJのリスト
	 */
	private List<DeviceObjectSuperClass> deviceEojList;

	/**
	 * コンストラクタ
	 * @param elProcess ELプロセスオブジェクト
	 * @param ipAddress IPアドレス
	 * @param nodeProfile ノードプロファイルオブジェクト
	 * @param deviceObjectList デバイスオブジェクトのリスト
	 */
	public ElOneNode(ElProcess elProcess, String ipAddress , NodeProfileClass nodeProfile, List<DeviceObjectSuperClass> deviceObjectList) {

		this.ipAddress = ipAddress;
		this.nodeProfile = nodeProfile;

		if (deviceObjectList == null) {
			this.deviceEojList = new ArrayList<DeviceObjectSuperClass>();
		} else {
			this.deviceEojList = deviceObjectList;
		}

		this.elProcess = elProcess;
	}


	/**
	 * 所属するELプロセスのオブジェクトを返す
	 * @return ElProcess ELプロセスオブジェクト
	 */
	public ElProcess getElProcess() {
		return this.elProcess;
	}

	/**
	 * IPアドレスを取得
	 * @return String型の値
	 */
	public String getIpAddress() {
		return this.ipAddress;
	}

	/**
	 * ノードプロファイルクラスオブジェクトを取得
	 * @return NodeProfileClass型の値
	 */
	public NodeProfileClass getNodeProfile() {
		return this.nodeProfile;
	}

	/**
	 * デバイスのEOJ一覧（リスト型）を取得
	 * @return List型の値
	 */
	public List<DeviceObjectSuperClass> getDeviceEojList() {
		return this.deviceEojList;
	}

	/**
	 * デバイスのEOJ一覧（リスト型）を取得（グループコード、クラスコードを指定）
	 * @param strClassGroupCode クラスグループコード
	 * @param strClassCode クラスコード
	 * @return List型の値
	 */
	public List<DeviceObjectSuperClass> getDeviceEojList(String strClassGroupCode, String strClassCode) {
		List<DeviceObjectSuperClass> retList = new ArrayList<DeviceObjectSuperClass>();
		for(DeviceObjectSuperClass eoj : this.getDeviceEojList()) {
			if(eoj.getStrClassGroupCode().equalsIgnoreCase(strClassGroupCode)
					&& eoj.getStrClassCode().equalsIgnoreCase(strClassCode)) {
				retList.add(eoj);
			}
		}
		return retList;
	}

	/**
	 * IPアドレスを設定
	 * ※Wi-Sunデバイス対応。Wi-SUNデバイスはリンクローカルアドレスを後からデバイスより取得する為。
	 * @param ipAddress IPアドレス
	 */
	public void setIpAddress(String  ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * 指定されたEOJオブジェクトを返す
	 * @param strClassGroupCode クラスグループコード
	 * @param strClassCode クラスコード
	 * @param strEntityCode エンティティコード
	 * @return EOJオブジェクト
	 */
	public ElClassBase getEoj(String strClassGroupCode, String strClassCode, String strEntityCode) {
		if(strClassGroupCode.equalsIgnoreCase(this.getNodeProfile().getStrClassGroupCode()) &&
				strClassCode.equalsIgnoreCase(this.getNodeProfile().getStrClassCode()) &&
				strEntityCode.equalsIgnoreCase(this.getNodeProfile().getStrEntityCode())){
			return this.getNodeProfile();
		}
		return this.getDeviceEoj(strClassGroupCode, strClassCode, strEntityCode);
	}
	/**
	 * 指定されたデバイスEOJオブジェクトを返す<br>
	 * 該当がない場合はnullを返す
	 * @param strClassGroupCode クラスグループコード
	 * @param strClassCode クラスコード
	 * @param strEntityCode エンティティコード
	 * @return デバイスEOJオブジェクト
	 */
	public DeviceObjectSuperClass getDeviceEoj(String strClassGroupCode, String strClassCode, String strEntityCode) {
		if (this.deviceEojList == null || this.deviceEojList.size() == 0) {
			return null; // 未登録の場合
		}
		for(DeviceObjectSuperClass device : this.getDeviceEojList()) {
			if (strClassGroupCode.equalsIgnoreCase(device.getStrClassGroupCode()) &&
					strClassCode.equalsIgnoreCase(device.getStrClassCode()) &&
					strEntityCode.equalsIgnoreCase(device.getStrEntityCode())){
				return device;
			}
		}
		return null; // 該当なし
	}

	/**
	 * デバイスのEOJ追加登録処理
	 * @param strClassGroupCode クラスグループコード
	 * @param strClassCode クラスコード
	 * @param strEntityCode エンティティコード
	 * @return デバイスのEOJオブジェクト
	 */
	public DeviceObjectSuperClass addDeviceEojByCode(String strClassGroupCode, String strClassCode, String strEntityCode) {
		// EOJオブジェクトを生成
		DeviceObjectSuperClass newEoj = ElClassBase.instanceDeviceEojByCode(strClassGroupCode, strClassCode, strEntityCode);

		// デバイスオブジェクトを一覧に追加
		deviceEojList.add(newEoj);
		
		// 追加したデバイスオブジェクトを返す
		return newEoj;
	}

	/**
	 * 新規ノード検出時のコールバック処理
	 */
	public void onNewNodeFound(){
		ElEventListener event = this.elProcess.getElBulkEventProcessor();
		if(event != null) {
			event.onNewNodeFound(this);
		}
	}

	/**
	 * ノード検出時のコールバック処理（新規性の有無にかかわらずコールされる）
	 */
	public void onNodeFound(){
		ElEventListener event = this.elProcess.getElBulkEventProcessor();
		if(event != null) {
			event.onNodeFound(this);
		}
	}

	/**
	 * ノード検出に関するイベント処理ハンドラ
	 */
	public static interface ElNodeEvent{

		/**
		 * 新規ノード検出時の処理ハンドラ
		 * @param node 検出されたノードオブジェクト
		 */
		public void onNewNodeFound(ElOneNode node);

		/**
		 * ノード検出時の処理ハンドラ（新規性の有無に関わらずコールされる）
		 * @param node 検出されたノードオブジェクト
		 */
		public void onNodeFound(ElOneNode node);

	}


}
