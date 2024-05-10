package jp.co.smartsolar.smartedge.echonetlite.devices;

import java.util.HashMap;
import java.util.Map;

import jp.co.smartsolar.smartedge.echonetlite.ElClassBase;
import jp.co.smartsolar.smartedge.echonetlite.ElFrame;
import jp.co.smartsolar.smartedge.echonetlite.ElProcess;
import jp.co.smartsolar.smartedge.echonetlite.ElProp;
import jp.co.smartsolar.smartedge.echonetlite.ElUtil;
import jp.co.smartsolar.smartedge.echonetlite.profile.NodeProfileClass;

/**
 * コントローラクラス
 */
public class ControllerClass extends DeviceObjectSuperClass{

	/**
	 * ログ出力用タグ
	 */
	@SuppressWarnings("unused")
	private static final String TAG = "ControllerClass";

	/**
	 *  クラスグループコード
	 */
	public static final String CLASS_GROUP_CODE = "05";
	/**
	 *  クラスコード
	 */
	public static final String CLASS_CODE = "FF";

	/**
	 * EPC : 動作状態
	 */
	public static final String EPC_OPERATION_STATUS = "80";
	/**
	 * EDT : 動作状態（ON）
	 */
	public static final String EDT_OPERATION_STATUS_ON = "30";
	/**
	 * EDT: 動作状態（OFF）
	 */
	public static final String EDT_OPERATION_STATUS_OFF = "31";


	/**
	 * EPC : コントローラーＩＤ
	 */
	public static final String EPC_CONTROLLER_ID = "C0";

	/**
	 * EPC : 管理台数
	 */
	public static final String EPC_MANAGED_DEVICE_NUM = "C1";

	/**
	 * EPC : インデックス
	 */
	public static final String EPC_INDEX = "C2";

	/**
	 * EPC : 機器ID
	 */
	public static final String EPC_DEVICE_ID = "C3";

	/**
	 * EPC : 機種
	 */
	public static final String EPC_DEVICE_TYPE = "C4";

	/**
	 * EPC : 名称
	 */
	public static final String EPC_DEVICE_NAME = "C5";

	/**
	 * EPC : 接続状態
	 */
	public static final String EPC_CONNECT_STATE = "C6";
	/**
	 * EDT: 動作状態（接続中）
	 */
	public static final String EDT_CONNECTED = "41";
	/**
	 * EDT: 動作状態（離脱中）
	 */
	public static final String EDT_DISCONNECTED = "42";
	/**
	 * EDT: 動作状態（未登録）
	 */
	public static final String EDT_NOT_REGISTERD = "43";
	/**
	 * EDT: 動作状態（削除）
	 */
	public static final String EDT_DELETED = "44";

	/**
	 * EPC : 管理対象機器事業者コード
	 */
	public static final String EPC_BUSINESS_CODE = "C7";

	/**
	 * EPC : 管理対象機器商品コード
	 */
	public static final String EPC_PRODUCT_CODE = "C8";

	/**
	 * EPC : 管理対象機器製造年月日
	 */
	public static final String EPC_MANUFACTURED_YMD = "C9";

	/**
	 * EPC : 管理対象機器登録情報更新年月日
	 */
	public static final String EPC_REGISTERED_YMD = "CA";

	/**
	 * EPC : 管理対象機器登録情報更新バージョン情報
	 */
	public static final String EPC_REGISTERED_INFO_RENEWAL_VERSION = "CB";

	/**
	 * EPC : 管理対象機器設置場所
	 */
	public static final String EPC_PLACE_TO_INSTALL = "CC";

	/**
	 * EPC : 管理対象機器異常発生状態
	 */
	public static final String EPC_FAULT_STATUS = "CD";
	/**
	 * EPC : 管理対象機器異常発生状態（異常発生有）
	 */
	public static final String EDT_FAULT_STATUS_TRUE = "41";
	/**
	 * EPC : 管理対象機器異常発生状態（異常発生無）
	 */
	public static final String EDT_FAULT_STATUS_FALSE = "42";

	/**
	 * EPC : 設置住所
	 */
	public static final String EPC_ADDRESS_OF_INSTALLATION_LOCATION = "E0";

	/**
	 * EPC : 管理対象機器Setプロパティマップ
	 */
	public static final String EPC_SET_PROPERTY_MAP = "CE";

	/**
	 * EPC : 管理対象機器Getプロパティマップ
	 */
	public static final String EPC_GET_PROPERTY_MAP = "CF";




	/**
	 * コンストラクタ
	 */
	public ControllerClass() {
		super(CLASS_GROUP_CODE, CLASS_CODE);
	}
	/**
	 * コンストラクタ
	 * @param entityCode エンティティコード（16進数文字列）
	 */
	public ControllerClass(String entityCode) {
		super(CLASS_GROUP_CODE, CLASS_CODE, entityCode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initPorpMaps(){
		super.initPorpMaps();

		this.addMapGetProps(EPC_OPERATION_STATUS);
//		this.addMapGetProps(EPC_CONTROLLER_ID); // 必須ではない
//		this.addMapGetProps(EPC_MANAGED_DEVICE_NUM); // 必須ではない
//		this.addMapGetProps(EPC_INDEX); // 必須ではない
//		this.addMapGetProps(EPC_DEVICE_ID); // 必須ではない
//		this.addMapGetProps(EPC_DEVICE_TYPE); // 必須ではない
//		this.addMapGetProps(EPC_DEVICE_NAME); // 必須ではない
//		this.addMapGetProps(EPC_CONNECT_STATE); // 必須ではない
//		this.addMapGetProps(EPC_BUSINESS_CODE); // 必須ではない
		this.addMapGetProps(EPC_PRODUCT_CODE); // 必須ではない
//		this.addMapGetProps(EPC_MANUFACTURED_YMD); // 必須ではない
//		this.addMapGetProps(EPC_REGISTERED_YMD); // 必須ではない
//		this.addMapGetProps(EPC_REGISTERED_INFO_RENEWAL_VERSION); // 必須ではない
//		this.addMapGetProps(EPC_PLACE_TO_INSTALL); // 必須ではない
//		this.addMapGetProps(EPC_FAULT_STATUS); // 必須ではない
//		this.addMapGetProps(EPC_ADDRESS_OF_INSTALLATION_LOCATION); // 必須ではない
//		this.addMapGetProps(EPC_SET_PROPERTY_MAP); // 必須ではない
//		this.addMapGetProps(EPC_GET_PROPERTY_MAP); // 必須ではない

//		this.addMapSetProps(EPC_OPERATION_STATUS); // 必須ではない
//		this.addMapSetProps(EPC_INDEX); // 必須ではない

		this.addMapConvAnnounceProps(EPC_OPERATION_STATUS);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onNewEojFound(){
		super.onNewEojFound();
		getElProcess().getElBulkEventProcessor().onNewControllerFound(this);
	}



	/**
	 * Set系処理の集約クラス
	 * 0x60 : プロパティ値書込み要求（応答不要）
	 * 0x61 : プロパティ値書込み要求（応答要）
	 */
	public static class ElSetProps extends DeviceObjectSuperClass.ElSetProps {

		/**
		 * ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "ControllerClass.ElSetProps";

		/**
		 * {@inheritDoc}
		 */
		public ElSetProps(ElProcess elProcess, String remoteHostName, ElClassBase objSeoj, ElClassBase objDeoj, String strEsv) {
			super(elProcess, remoteHostName, objSeoj, objDeoj, strEsv);
		}

		/**
		 * 動作状態<br>
		 * EPC                 : 0x80<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ON／OFF の状態を示す。<br>
		 * ＯＮ＝0x30，ＯＦＦ＝0x31<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return ControllerClass.ElSetPropsオブジェクト
		 */
		public ControllerClass.ElSetProps reqSetOperationStatus(String strEdt){
			listProperty.add(new ElProp(EPC_OPERATION_STATUS, strEdt));
			return this;
		}
		/**
		 * インデックス<br>
		 * EPC                 : 0xC2<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器リストのインデックスを示す<br>
		 * 0x0001～0xFFFD (1～65533)<br>
		 * <br>
		 * @param strEdt EDT値（16進数文字列）
		 * @return ControllerClass.ElSetPropsオブジェクト
		 */
		public ControllerClass.ElSetProps reqSetIndex(String strEdt){
			listProperty.add(new ElProp(EPC_INDEX, strEdt));
			return this;
		}

	}

	/**
	 * Get系処理の集約クラス
	 * 0x62 : プロパティ値読出し要求
	 */
	public static class ElGetProps extends DeviceObjectSuperClass.ElGetProps  {

		/**
		 * ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "ControllerClass.ElGetProps";

		/**
		 * {@inheritDoc}
		 */
		public ElGetProps(ElProcess elProcess, String remoteHostName, ElClassBase objSeoj, ElClassBase objDeoj, String strEsv) {
			super(elProcess, remoteHostName, objSeoj, objDeoj, strEsv);
		}

		/**
		 * 動作状態<br>
		 * EPC                 : 0x80<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ON／OFF の状態を示す。<br>
		 * ＯＮ＝0x30，ＯＦＦ＝0x31<br>
		 * <br>
		 * @return ControllerClass.ElGetPropsオブジェクト
		 */
		public ControllerClass.ElGetProps reqGetOperationStatus(){
			listProperty.add(new ElProp(EPC_OPERATION_STATUS));
			return this;
		}
		/**
		 * コントローラID<br>
		 * EPC                 : 0xC0<br>
		 * データタイプ        : unsigned char x 40<br>
		 * サイズ              : Max 40 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
		 * <br>
		 * コントローラのID を示す<br>
		 * 最大40 バイトのバイナリ値<br>
		 * <br>
		 * @return ControllerClass.ElGetPropsオブジェクト
		 */
		public ControllerClass.ElGetProps reqGetControllerId(){
			listProperty.add(new ElProp(EPC_CONTROLLER_ID));
			return this;
		}
		/**
		 * 管理台数<br>
		 * EPC                 : 0xC1<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
		 * <br>
		 * 該当するコントローラID のコントローラが管理する機器の台数<br>
		 * 0x0000～0xFFFD (0～65533)<br>
		 * <br>
		 * @return ControllerClass.ElGetPropsオブジェクト
		 */
		public ControllerClass.ElGetProps reqGetManagedDeviceNum(){
			listProperty.add(new ElProp(EPC_MANAGED_DEVICE_NUM));
			return this;
		}
		/**
		 * インデックス<br>
		 * EPC                 : 0xC2<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器リストのインデックスを示す<br>
		 * 0x0001～0xFFFD (1～65533)<br>
		 * <br>
		 * @return ControllerClass.ElGetPropsオブジェクト
		 */
		public ControllerClass.ElGetProps reqGetIndex(){
			listProperty.add(new ElProp(EPC_INDEX));
			return this;
		}
		/**
		 * 機器ID<br>
		 * EPC                 : 0xC3<br>
		 * データタイプ        : unsigned char x 40<br>
		 * サイズ              : Max 40 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
		 * <br>
		 * 機器のID を示す<br>
		 * 最大40 バイトのバイナリ値<br>
		 * <br>
		 * @return ControllerClass.ElGetPropsオブジェクト
		 */
		public ControllerClass.ElGetProps reqGetDeviceId(){
			listProperty.add(new ElProp(EPC_DEVICE_ID));
			return this;
		}
		/**
		 * 機種<br>
		 * EPC                 : 0xC4<br>
		 * データタイプ        : unsigned char x 2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
		 * <br>
		 * 機器ID が示す機器の機種を示す。<br>
		 * クラスグループコード＋クラスコード<br>
		 * <br>
		 * @return ControllerClass.ElGetPropsオブジェクト
		 */
		public ControllerClass.ElGetProps reqGetDeviceType(){
			listProperty.add(new ElProp(EPC_DEVICE_TYPE));
			return this;
		}
		/**
		 * 名称<br>
		 * EPC                 : 0xC5<br>
		 * データタイプ        : unsigned char x 64<br>
		 * サイズ              : Max 64 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器ID が示す機器の名称を示す。<br>
		 * UTF-8 (BOM 無し)で指定<br>
		 * <br>
		 * @return ControllerClass.ElGetPropsオブジェクト
		 */
		public ControllerClass.ElGetProps reqGetDeviceName(){
			listProperty.add(new ElProp(EPC_DEVICE_NAME));
			return this;
		}
		/**
		 * 接続状態<br>
		 * EPC                 : 0xC6<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
		 * <br>
		 * 機器ID が示す機器の状態を示す。<br>
		 * 接続中 = 0x41, 離脱中 =0x42, 未登録 = 0x43、削除=0x44<br>
		 * <br>
		 * @return ControllerClass.ElGetPropsオブジェクト
		 */
		public ControllerClass.ElGetProps reqGetConnectState(){
			listProperty.add(new ElProp(EPC_CONNECT_STATE));
			return this;
		}
		/**
		 * 管理対象機器事業者コード<br>
		 * EPC                 : 0xC7<br>
		 * データタイプ        : unsigned char x 3<br>
		 * サイズ              : 3 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
		 * <br>
		 * 3 バイトで指定。<br>
		 * (ECHONET ｺﾝｿｰｼｱﾑで規定。)<br>
		 * <br>
		 * @return ControllerClass.ElGetPropsオブジェクト
		 */
		public ControllerClass.ElGetProps reqGetBusinessCode(){
			listProperty.add(new ElProp(EPC_BUSINESS_CODE));
			return this;
		}
		/**
		 * 管理対象機器商品コード<br>
		 * EPC                 : 0xC8<br>
		 * データタイプ        : unsigned char x 12<br>
		 * サイズ              : Max 12 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ASCII コードで指定。<br>
		 * （各メーカ毎に規定。）<br>
		 * <br>
		 * @return ControllerClass.ElGetPropsオブジェクト
		 */
		public ControllerClass.ElGetProps reqGetProductCode(){
			listProperty.add(new ElProp(EPC_PRODUCT_CODE));
			return this;
		}
		/**
		 * 管理対象機器製造年月日<br>
		 * EPC                 : 0xC9<br>
		 * データタイプ        : unsigned char x 4<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 4 バイトで指定。<br>
		 * YYMD(1 文字1 ﾊﾞｲﾄ)で示す。<br>
		 * YY ： 西暦年(1999 年の場合:0x07CF)<br>
		 * M：月(12 月の場合＝0x0C)<br>
		 * D：日(20 日の場合＝0x14)<br>
		 * <br>
		 * @return ControllerClass.ElGetPropsオブジェクト
		 */
		public ControllerClass.ElGetProps reqGetManufacturedYmd(){
			listProperty.add(new ElProp(EPC_MANUFACTURED_YMD));
			return this;
		}
		/**
		 * 管理対象機器登録情報更新年月日<br>
		 * EPC                 : 0xCA<br>
		 * データタイプ        : unsigned char x 4<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 4 バイトで指定。<br>
		 * YYMD(1 文字1 ﾊﾞｲﾄ)で示す。<br>
		 * YY ： 西暦年(1999 年の場合:0x07CF)<br>
		 * M：月(12 月の場合＝0x0C)<br>
		 * D：日(20 日の場合＝0x14)<br>
		 * <br>
		 * @return ControllerClass.ElGetPropsオブジェクト
		 */
		public ControllerClass.ElGetProps reqGetRegisteredYmd(){
			listProperty.add(new ElProp(EPC_REGISTERED_YMD));
			return this;
		}
		/**
		 * 管理対象機器登録情報更新バージョン情報<br>
		 * EPC                 : 0xCB<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 管理する機器が追加、削除されるたびに、更新するバージョン情報<br>
		 * 0x0000～0xFFFD (0～65533)<br>
		 * <br>
		 * @return ControllerClass.ElGetPropsオブジェクト
		 */
		public ControllerClass.ElGetProps reqGetRegisteredInfoRenewalVersion(){
			listProperty.add(new ElProp(EPC_REGISTERED_INFO_RENEWAL_VERSION));
			return this;
		}
		/**
		 * 管理対象機器設置場所<br>
		 * EPC                 : 0xCC<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器ID が示す機器の設置場所を示す<br>
		 * 2.2 設置場所プロパティを参照<br>
		 * <br>
		 * @return ControllerClass.ElGetPropsオブジェクト
		 */
		public ControllerClass.ElGetProps reqGetPlaceToInstall(){
			listProperty.add(new ElProp(EPC_PLACE_TO_INSTALL));
			return this;
		}
		/**
		 * 管理対象機器異常発生状態<br>
		 * EPC                 : 0xCD<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器ID が示す機器の異常発生状態を示す<br>
		 * 異常発生有0x41、異常発生無0x42<br>
		 * <br>
		 * @return ControllerClass.ElGetPropsオブジェクト
		 */
		public ControllerClass.ElGetProps reqGetFaultStatus(){
			listProperty.add(new ElProp(EPC_FAULT_STATUS));
			return this;
		}
		/**
		 * 設置住所<br>
		 * EPC                 : 0xE0<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : Max.255 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * コントローラが設置されている場所の住所を示す。<br>
		 * UTF-8 (BOM 無し)で指定<br>
		 * <br>
		 * @return ControllerClass.ElGetPropsオブジェクト
		 */
		public ControllerClass.ElGetProps reqGetAddressOfInstallationLocation(){
			listProperty.add(new ElProp(EPC_ADDRESS_OF_INSTALLATION_LOCATION));
			return this;
		}
		/**
		 * 管理対象機器Setプロパティマップ<br>
		 * EPC                 : 0xCE<br>
		 * データタイプ        : unsigned char x (Max. 17)<br>
		 * サイズ              : Max.17 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器ID が示す機器のSet プロパティマップを示す。<br>
		 * 付録１を参照のこと<br>
		 * <br>
		 * @return ControllerClass.ElGetPropsオブジェクト
		 */
		public ControllerClass.ElGetProps reqGetSetPropertyMap(){
			listProperty.add(new ElProp(EPC_SET_PROPERTY_MAP));
			return this;
		}
		/**
		 * 管理対象機器Getプロパティマップ<br>
		 * EPC                 : 0xCF<br>
		 * データタイプ        : unsigned char x (Max. 17) byte<br>
		 * サイズ              : Max.17 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器ID が示す機器のGet プロパティマップを示す。<br>
		 * 付録１を参照のこと<br>
		 * <br>
		 * @return ControllerClass.ElGetPropsオブジェクト
		 */
		public ControllerClass.ElGetProps reqGetGetPropertyMap(){
			listProperty.add(new ElProp(EPC_GET_PROPERTY_MAP));
			return this;
		}

	}

	/**
	 * 通知系処理の集約クラス
	 * 0x73 : プロパティ値通知
	 * 0x63 : プロパティ値通知要求
	 */
	public static class ElInformProps extends DeviceObjectSuperClass.ElInformProps  {

		/**
		 * ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "ControllerClass.ElInformProps";

		/**
		 * {@inheritDoc}
		 */
		public ElInformProps(ElProcess elProcess, String remoteHostName, ElClassBase objSeoj, ElClassBase objDeoj, String strEsv) {
			super(elProcess, remoteHostName, objSeoj, objDeoj, strEsv);
		}

		/**
		 * 動作状態<br>
		 * EPC                 : 0x80<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ON／OFF の状態を示す。<br>
		 * ＯＮ＝0x30，ＯＦＦ＝0x31<br>
		 * <br>
		 * @return ControllerClass.ElInformPropsオブジェクト
		 */
		public ControllerClass.ElInformProps reqInfOperationStatus(){
			listProperty.add(new ElProp(EPC_OPERATION_STATUS));
			return this;
		}
		/**
		 * コントローラID<br>
		 * EPC                 : 0xC0<br>
		 * データタイプ        : unsigned char x 40<br>
		 * サイズ              : Max 40 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
		 * <br>
		 * コントローラのID を示す<br>
		 * 最大40 バイトのバイナリ値<br>
		 * <br>
		 * @return ControllerClass.ElInformPropsオブジェクト
		 */
		public ControllerClass.ElInformProps reqInfControllerId(){
			listProperty.add(new ElProp(EPC_CONTROLLER_ID));
			return this;
		}
		/**
		 * 管理台数<br>
		 * EPC                 : 0xC1<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
		 * <br>
		 * 該当するコントローラID のコントローラが管理する機器の台数<br>
		 * 0x0000～0xFFFD (0～65533)<br>
		 * <br>
		 * @return ControllerClass.ElInformPropsオブジェクト
		 */
		public ControllerClass.ElInformProps reqInfManagedDeviceNum(){
			listProperty.add(new ElProp(EPC_MANAGED_DEVICE_NUM));
			return this;
		}
		/**
		 * インデックス<br>
		 * EPC                 : 0xC2<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器リストのインデックスを示す<br>
		 * 0x0001～0xFFFD (1～65533)<br>
		 * <br>
		 * @return ControllerClass.ElInformPropsオブジェクト
		 */
		public ControllerClass.ElInformProps reqInfIndex(){
			listProperty.add(new ElProp(EPC_INDEX));
			return this;
		}
		/**
		 * 機器ID<br>
		 * EPC                 : 0xC3<br>
		 * データタイプ        : unsigned char x 40<br>
		 * サイズ              : Max 40 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
		 * <br>
		 * 機器のID を示す<br>
		 * 最大40 バイトのバイナリ値<br>
		 * <br>
		 * @return ControllerClass.ElInformPropsオブジェクト
		 */
		public ControllerClass.ElInformProps reqInfDeviceId(){
			listProperty.add(new ElProp(EPC_DEVICE_ID));
			return this;
		}
		/**
		 * 機種<br>
		 * EPC                 : 0xC4<br>
		 * データタイプ        : unsigned char x 2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
		 * <br>
		 * 機器ID が示す機器の機種を示す。<br>
		 * クラスグループコード＋クラスコード<br>
		 * <br>
		 * @return ControllerClass.ElInformPropsオブジェクト
		 */
		public ControllerClass.ElInformProps reqInfDeviceType(){
			listProperty.add(new ElProp(EPC_DEVICE_TYPE));
			return this;
		}
		/**
		 * 名称<br>
		 * EPC                 : 0xC5<br>
		 * データタイプ        : unsigned char x 64<br>
		 * サイズ              : Max 64 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器ID が示す機器の名称を示す。<br>
		 * UTF-8 (BOM 無し)で指定<br>
		 * <br>
		 * @return ControllerClass.ElInformPropsオブジェクト
		 */
		public ControllerClass.ElInformProps reqInfDeviceName(){
			listProperty.add(new ElProp(EPC_DEVICE_NAME));
			return this;
		}
		/**
		 * 接続状態<br>
		 * EPC                 : 0xC6<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
		 * <br>
		 * 機器ID が示す機器の状態を示す。<br>
		 * 接続中 = 0x41, 離脱中 =0x42, 未登録 = 0x43、削除=0x44<br>
		 * <br>
		 * @return ControllerClass.ElInformPropsオブジェクト
		 */
		public ControllerClass.ElInformProps reqInfConnectState(){
			listProperty.add(new ElProp(EPC_CONNECT_STATE));
			return this;
		}
		/**
		 * 管理対象機器事業者コード<br>
		 * EPC                 : 0xC7<br>
		 * データタイプ        : unsigned char x 3<br>
		 * サイズ              : 3 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
		 * <br>
		 * 3 バイトで指定。<br>
		 * (ECHONET ｺﾝｿｰｼｱﾑで規定。)<br>
		 * <br>
		 * @return ControllerClass.ElInformPropsオブジェクト
		 */
		public ControllerClass.ElInformProps reqInfBusinessCode(){
			listProperty.add(new ElProp(EPC_BUSINESS_CODE));
			return this;
		}
		/**
		 * 管理対象機器商品コード<br>
		 * EPC                 : 0xC8<br>
		 * データタイプ        : unsigned char x 12<br>
		 * サイズ              : Max 12 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ASCII コードで指定。<br>
		 * （各メーカ毎に規定。）<br>
		 * <br>
		 * @return ControllerClass.ElInformPropsオブジェクト
		 */
		public ControllerClass.ElInformProps reqInfProductCode(){
			listProperty.add(new ElProp(EPC_PRODUCT_CODE));
			return this;
		}
		/**
		 * 管理対象機器製造年月日<br>
		 * EPC                 : 0xC9<br>
		 * データタイプ        : unsigned char x 4<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 4 バイトで指定。<br>
		 * YYMD(1 文字1 ﾊﾞｲﾄ)で示す。<br>
		 * YY ： 西暦年(1999 年の場合:0x07CF)<br>
		 * M：月(12 月の場合＝0x0C)<br>
		 * D：日(20 日の場合＝0x14)<br>
		 * <br>
		 * @return ControllerClass.ElInformPropsオブジェクト
		 */
		public ControllerClass.ElInformProps reqInfManufacturedYmd(){
			listProperty.add(new ElProp(EPC_MANUFACTURED_YMD));
			return this;
		}
		/**
		 * 管理対象機器登録情報更新年月日<br>
		 * EPC                 : 0xCA<br>
		 * データタイプ        : unsigned char x 4<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 4 バイトで指定。<br>
		 * YYMD(1 文字1 ﾊﾞｲﾄ)で示す。<br>
		 * YY ： 西暦年(1999 年の場合:0x07CF)<br>
		 * M：月(12 月の場合＝0x0C)<br>
		 * D：日(20 日の場合＝0x14)<br>
		 * <br>
		 * @return ControllerClass.ElInformPropsオブジェクト
		 */
		public ControllerClass.ElInformProps reqInfRegisteredYmd(){
			listProperty.add(new ElProp(EPC_REGISTERED_YMD));
			return this;
		}
		/**
		 * 管理対象機器登録情報更新バージョン情報<br>
		 * EPC                 : 0xCB<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 管理する機器が追加、削除されるたびに、更新するバージョン情報<br>
		 * 0x0000～0xFFFD (0～65533)<br>
		 * <br>
		 * @return ControllerClass.ElInformPropsオブジェクト
		 */
		public ControllerClass.ElInformProps reqInfRegisteredInfoRenewalVersion(){
			listProperty.add(new ElProp(EPC_REGISTERED_INFO_RENEWAL_VERSION));
			return this;
		}
		/**
		 * 管理対象機器設置場所<br>
		 * EPC                 : 0xCC<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器ID が示す機器の設置場所を示す<br>
		 * 2.2 設置場所プロパティを参照<br>
		 * <br>
		 * @return ControllerClass.ElInformPropsオブジェクト
		 */
		public ControllerClass.ElInformProps reqInfPlaceToInstall(){
			listProperty.add(new ElProp(EPC_PLACE_TO_INSTALL));
			return this;
		}
		/**
		 * 管理対象機器異常発生状態<br>
		 * EPC                 : 0xCD<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器ID が示す機器の異常発生状態を示す<br>
		 * 異常発生有0x41、異常発生無0x42<br>
		 * <br>
		 * @return ControllerClass.ElInformPropsオブジェクト
		 */
		public ControllerClass.ElInformProps reqInfFaultStatus(){
			listProperty.add(new ElProp(EPC_FAULT_STATUS));
			return this;
		}
		/**
		 * 設置住所<br>
		 * EPC                 : 0xE0<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : Max.255 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * コントローラが設置されている場所の住所を示す。<br>
		 * UTF-8 (BOM 無し)で指定<br>
		 * <br>
		 * @return ControllerClass.ElInformPropsオブジェクト
		 */
		public ControllerClass.ElInformProps reqInfAddressOfInstallationLocation(){
			listProperty.add(new ElProp(EPC_ADDRESS_OF_INSTALLATION_LOCATION));
			return this;
		}
		/**
		 * 管理対象機器Setプロパティマップ<br>
		 * EPC                 : 0xCE<br>
		 * データタイプ        : unsigned char x (Max. 17)<br>
		 * サイズ              : Max.17 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器ID が示す機器のSet プロパティマップを示す。<br>
		 * 付録１を参照のこと<br>
		 * <br>
		 * @return ControllerClass.ElInformPropsオブジェクト
		 */
		public ControllerClass.ElInformProps reqInfSetPropertyMap(){
			listProperty.add(new ElProp(EPC_SET_PROPERTY_MAP));
			return this;
		}
		/**
		 * 管理対象機器Getプロパティマップ<br>
		 * EPC                 : 0xCF<br>
		 * データタイプ        : unsigned char x (Max. 17) byte<br>
		 * サイズ              : Max.17 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器ID が示す機器のGet プロパティマップを示す。<br>
		 * 付録１を参照のこと<br>
		 * <br>
		 * @return ControllerClass.ElInformPropsオブジェクト
		 */
		public ControllerClass.ElInformProps reqInfGetPropertyMap(){
			listProperty.add(new ElProp(EPC_GET_PROPERTY_MAP));
			return this;
		}
	}

	/**
	 * SetIサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : SetI<br>
	 * DEOJ : 本クラスのEOJ(全インスタンス指定)<br>
	 * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @return ControllerClass.ElSetPropsオブジェクト
	 */
	public static ControllerClass.ElSetProps setIAll(ElProcess elProcess) {
		return setIAll(elProcess, INSTANCE_CODE_ALL);
	}

	/**
	 * SetIサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : SetI<br>
	 * DEOJ : 本クラスのEOJ(全インスタンス指定)<br>
	 * SEOJ : ＜パラメータ指定＞<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @param seoj 送信元EOJ
	 * @return ControllerClass.ElSetPropsオブジェクト
	 */
	public static ControllerClass.ElSetProps setIAll(ElProcess elProcess, ElClassBase seoj) {
		return setIAll(elProcess, seoj, INSTANCE_CODE_ALL);
	}

	/**
	 * SetIサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : SetI<br>
	 * DEOJ : 本クラスのEOJ(インスタンスコードはパラメータ指定)<br>
	 * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @param instanceCode インスタンスコード
	 * @return ControllerClass.ElSetPropsオブジェクト
	 */
	public static ControllerClass.ElSetProps setIAll(ElProcess elProcess, String instanceCode) {
		return setIAll(elProcess, getLocalNodeProfile(elProcess), instanceCode);
	}

	/**
	 * SetIサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : SetI<br>
	 * DEOJ : 本クラスのEOJ(インスタンスコードはパラメータ指定)<br>
	 * SEOJ : ＜パラメータ指定＞<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @param seoj 送信元EOJ
	 * @param instanceCode インスタンスコード
	 * @return ControllerClass.ElSetPropsオブジェクト
	 */
	public static ControllerClass.ElSetProps setIAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new ElSetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new ControllerClass(instanceCode), ElFrame.ESV_SETI);
	}

	/**
	 * SetCサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : SetC<br>
	 * DEOJ : 本クラスのEOJ(全インスタンス指定)<br>
	 * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @return ControllerClass.ElSetPropsオブジェクト
	 */
	public static ControllerClass.ElSetProps setCAll(ElProcess elProcess) {
		return setCAll(elProcess, INSTANCE_CODE_ALL);
	}

	/**
	 * SetCサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : SetC<br>
	 * DEOJ : 本クラスのEOJ(全インスタンス指定)<br>
	 * SEOJ : ＜パラメータ指定＞<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @param seoj 送信元EOJ
	 * @return ControllerClass.ElSetPropsオブジェクト
	 */
	public static ControllerClass.ElSetProps setCAll(ElProcess elProcess, ElClassBase seoj) {
		return setCAll(elProcess, seoj, INSTANCE_CODE_ALL);
	}

	/**
	 * SetCサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : SetC<br>
	 * DEOJ : 本クラスのEOJ(インスタンスコードはパラメータ指定)<br>
	 * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @param instanceCode インスタンスコード
	 * @return ControllerClass.ElSetPropsオブジェクト
	 */
	public static ControllerClass.ElSetProps setCAll(ElProcess elProcess, String instanceCode) {
		return setCAll(elProcess, getLocalNodeProfile(elProcess), instanceCode);
	}

	/**
	 * SetCサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : SetC<br>
	 * DEOJ : 本クラスのEOJ(インスタンスコードはパラメータ指定)<br>
	 * SEOJ : ＜パラメータ指定＞<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @param seoj 送信元EOJ
	 * @param instanceCode インスタンスコード
	 * @return ControllerClass.ElSetPropsオブジェクト
	 */
	public static ControllerClass.ElSetProps setCAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new ElSetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new ControllerClass(instanceCode), ElFrame.ESV_SETC);
	}

	/**
	 * Getサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : Get<br>
	 * DEOJ : 本クラスのEOJ(全インスタンス指定)<br>
	 * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @return ControllerClass.ElGetPropsオブジェクト
	 */
	public static ControllerClass.ElGetProps getAll(ElProcess elProcess) {
		return getAll( elProcess,  INSTANCE_CODE_ALL);
	}

	/**
	 * Getサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : Get<br>
	 * DEOJ : EOJ(全インスタンス指定)<br>
	 * SEOJ : ＜パラメータ指定＞<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @param seoj 送信元EOJ
	 * @return ControllerClass.ElGetPropsオブジェクト
	 */
	public static ControllerClass.ElGetProps getAll(ElProcess elProcess, ElClassBase seoj ) {
		return getAll( elProcess, seoj, INSTANCE_CODE_ALL);
	}

	/**
	 * Getサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : Get<br>
	 * DEOJ : 本クラスのEOJ(インスタンスコードはパラメータ指定)<br>
	 * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @param instanceCode インスタンスコード
	 * @return ControllerClass.ElGetPropsオブジェクト
	 */
	public static ControllerClass.ElGetProps getAll(ElProcess elProcess, String instanceCode) {
		return getAll(elProcess, getLocalNodeProfile(elProcess), instanceCode);
	}

	/**
	 * Getサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : Get<br>
	 * DEOJ : 本クラスのEOJ(インスタンスコードはパラメータ指定)<br>
	 * SEOJ : ＜パラメータ指定＞<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @param seoj 送信元EOJ
	 * @param instanceCode インスタンスコード
	 * @return ControllerClass.ElGetPropsオブジェクト
	 */
	public static ControllerClass.ElGetProps getAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new ElGetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new ControllerClass(instanceCode), ElFrame.ESV_GET);
	}

	/**
	 * INF_REQサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : INF_REQ<br>
	 * DEOJ : 本クラスのEOJ(全インスタンス指定)<br>
	 * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @return ControllerClass.ElInformPropsオブジェクト
	 */
	public static ControllerClass.ElInformProps infReqAll(ElProcess elProcess) {
		return infReqAll(elProcess, INSTANCE_CODE_ALL);
	}

	/**
	 * INF_REQサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : INF_REQ<br>
	 * DEOJ : EOJ(全インスタンス指定)<br>
	 * SEOJ : ＜パラメータ指定＞<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @param seoj 送信元EOJ
	 * @return ControllerClass.ElInformPropsオブジェクト
	 */
	public static ControllerClass.ElInformProps infReqAll(ElProcess elProcess, ElClassBase seoj ) {
		return infReqAll(elProcess, seoj, INSTANCE_CODE_ALL);
	}

	/**
	 * INF_REQサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : INF_REQ<br>
	 * DEOJ : 本クラスのEOJ(インスタンスコードはパラメータ指定)<br>
	 * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @param instanceCode インスタンスコード
	 * @return ControllerClass.ElInformPropsオブジェクト
	 */
	public static ControllerClass.ElInformProps infReqAll(ElProcess elProcess, String instanceCode) {
		return infReqAll( elProcess,  getLocalNodeProfile(elProcess), instanceCode);
	}

	/**
	 * INF_REQサービスの処理の指定<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : INF_REQ<br>
	 * DEOJ : 本クラスのEOJ(インスタンスコードはパラメータ指定)<br>
	 * SEOJ : ＜パラメータ指定＞<br>
	 * <br>
	 * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
	 * <br>
	 * @param elProcess ELプロセスオブジェクト
	 * @param seoj 送信元EOJ
	 * @param instanceCode インスタンスコード
	 * @return ControllerClass.ElInformPropsオブジェクト
	 */
	public static ControllerClass.ElInformProps infReqAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new ElInformProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new ControllerClass(instanceCode), ElFrame.ESV_INF_REQ);
	}

	/**
	 * SetIサービスの処理の指定（個別エンティティ宛て）<br>
	 * リモートIP : DEOJに紐づくIPアドレス<br>
	 * ESV : SetI<br>
	 * DEOJ : this(=リモートノードのEOJ)<br>
	 * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
	 * <br>
	 * 自身thisがリモートノードのEOJ(DEOJ)である前提<br>
	 * <br>
	 * @return ControllerClass.ElSetPropsオブジェクト
	 */
	@Override
	public ControllerClass.ElSetProps setI(){
		return  setI(getLocalNodeProfile(getElProcess()));
	}

	/**
	 * SetIサービスの処理の指定（個別エンティティ宛て）<br>
	 * リモートIP : DEOJに紐づくIPアドレス<br>
	 * ESV : SetI<br>
	 * DEOJ : this(=リモートノードのEOJ)<br>
	 * SEOJ : ＜パラメータ指定＞<br>
	 * <br>
	 * 自身thisがリモートノードのEOJ(DEOJ)である前提<br>
	 * <br>
	 * @param seoj 送信元EOJ
	 * @return ControllerClass.ElSetPropsオブジェクト
	 */
	@Override
	public ControllerClass.ElSetProps setI(ElClassBase seoj){
		return new ElSetProps(getElProcess(), this.getNodeBelongsTo().getIpAddress(), seoj, this, ElFrame.ESV_SETI);
	}

	/**
	 * SetCサービスの処理の指定（個別エンティティ宛て）<br>
	 * リモートIP : DEOJに紐づくIPアドレス<br>
	 * ESV : SetC<br>
	 * DEOJ : this(=リモートノードのEOJ)<br>
	 * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
	 * <br>
	 * 自身thisがリモートノードのEOJ(DEOJ)である前提<br>
	 * <br>
	 * @return ControllerClass.ElSetPropsオブジェクト
	 */
	@Override
	public ControllerClass.ElSetProps setC(){
		return  setC(getLocalNodeProfile(getElProcess()));
	}

	/**
	 * SetCサービスの処理の指定（個別エンティティ宛て）<br>
	 * リモートIP : DEOJに紐づくIPアドレス<br>
	 * ESV : SetC<br>
	 * DEOJ : this(=リモートノードのEOJ)<br>
	 * SEOJ : ＜パラメータ指定＞<br>
	 * <br>
	 * 自身thisがリモートノードのEOJ(DEOJ)である前提<br>
	 * <br>
	 * @param seoj 送信元EOJ
	 * @return ControllerClass.ElSetPropsオブジェクト
	 */
	@Override
	public ControllerClass.ElSetProps setC(ElClassBase seoj){
		return new ElSetProps(getElProcess(), this.getNodeBelongsTo().getIpAddress(), seoj, this, ElFrame.ESV_SETC);
	}

	/**
	 * Getサービスの処理の指定（個別エンティティ宛て）<br>
	 * リモートIP : DEOJに紐づくIPアドレス<br>
	 * ESV : Get<br>
	 * DEOJ : this(=リモートノードのEOJ)<br>
	 * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
	 * <br>
	 * 自身thisがリモートノードのEOJ(DEOJ)である前提<br>
	 * <br>
	 * @return ControllerClass.ElGetPropsオブジェクト
	 */
	@Override
	public ControllerClass.ElGetProps get(){
		return get(getLocalNodeProfile(getElProcess()));
	}

	/**
	 * Getサービスの処理の指定（個別エンティティ宛て）<br>
	 * リモートIP : DEOJに紐づくIPアドレス<br>
	 * ESV : Get<br>
	 * DEOJ : this(=リモートノードのEOJ)<br>
	 * SEOJ : ＜パラメータ指定＞<br>
	 * <br>
	 * 自身thisがリモートノードのEOJ(DEOJ)である前提<br>
	 * <br>
	 * @param seoj 送信元EOJ
	 * @return ControllerClass.ElGetPropsオブジェクト
	 */
	@Override
	public ControllerClass.ElGetProps get(ElClassBase seoj){
		return new ElGetProps(getElProcess(), this.getNodeBelongsTo().getIpAddress(), seoj, this, ElFrame.ESV_GET);
	}

	/**
	 * INF_REQサービスの処理の指定（個別エンティティ宛て）<br>
	 * リモートIP : DEOJに紐づくIPアドレス<br>
	 * ESV : INF_REQ<br>
	 * DEOJ : this(=リモートノードのEOJ)<br>
	 * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
	 * <br>
	 * 自身thisがリモートノードのEOJ(DEOJ)である前提<br>
	 * <br>
	 * @return ControllerClass.ElInformPropsオブジェクト
	 */
	@Override
	public ControllerClass.ElInformProps infReq(){
		return this.infReq(getLocalNodeProfile(getElProcess()));
	}

	/**
	 * INF_REQサービスの処理を指定（個別エンティティ宛て）<br>
	 * リモートIP : DEOJに紐づくIPアドレス<br>
	 * ESV : INF_REQ<br>
	 * DEOJ : this(=リモートノードのEOJ)<br>
	 * SEOJ : ＜パラメータ指定＞<br>
	 * <br>
	 * 自身thisがリモートノードのEOJ(DEOJ)である前提<br>
	 * <br>
	 * @param seoj 送信元EOJ
	 * @return ControllerClass.ElInformPropsオブジェクト
	 */
	@Override
	public ControllerClass.ElInformProps infReq(ElClassBase seoj){
		return new ElInformProps(getElProcess(), this.getNodeBelongsTo().getIpAddress(), seoj, this, ElFrame.ESV_INF_REQ);
	}

	/**
	 * INFサービスの処理を指定（マルチキャスト）<br>
	 * リモートIP : マルチキャストアドレス<br>
	 * ESV : INF<br>
	 * DEOJ : NodeProfileオブジェクト（一般ノード）<br>
	 * SEOJ : this(=ローカルノードのEOJ)<br>
	 * <br>
	 * 自身thisがローカルノードのEOJ(SEOJ)である前提<br>
	 * <br>
	 * @return ControllerClass.ElInformPropsオブジェクト
	 */
	@Override
	public ControllerClass.ElInformProps inf(){
		return inf(new NodeProfileClass());// INSTANCE_CODE_GENERAL
	}

	/**
	 * INFサービスの処理を指定<br>
	 * リモートIP : DEOJに紐づくIPアドレスまたは、マルチキャストアドレス<br>
	 * ただし、DEOJに紐づくIPアドレスが取得できない場合はマルチキャストアドレスとなる<br>
	 * ESV : INF<br>
	 * DEOJ : ＜パラメータ指定＞<br>
	 * SEOJ : this(=ローカルノードのEOJ)<br>
	 * <br>
	 * 自身thisがローカルノードのEOJ(SEOJ)である前提<br>
	 * <br>
	 * @param deoj 宛先EOJ
	 * @return ControllerClass.ElInformPropsオブジェクト
	 */
	@Override
	public ControllerClass.ElInformProps inf(ElClassBase deoj){

		boolean isWithMuticalst = false;
		// deojで所属するノードのIPアドレスが取得できない場合は、無条件にマルチキャストとする。
		if(deoj.getNodeBelongsTo() == null
				|| deoj.getNodeBelongsTo().getIpAddress() == null
				|| deoj.getNodeBelongsTo().getIpAddress().isEmpty()) {
			isWithMuticalst = true;
		}

		// 宛先IP
		String sIp = getElProcess().getChannel().getMultiCastAddress();
		ElClassBase objDeoj = deoj;
		if( isWithMuticalst) {
			// DEOJのエンティティコード部分を00に変更する。
			// 下記により、管理下のDEOJオブジェクトを変更せずに新規でEOJを生成する
			// objDeoj = ElClassBase.instanceEojByCode(deoj.getStrClassGroupCode(), deoj.getStrClassCode(), ElClassBase.INSTANCE_CODE_ALL);
		} else {
			sIp = deoj.getNodeBelongsTo().getIpAddress();
		}
		return new ElInformProps(getElProcess(), sIp, this, objDeoj, ElFrame.ESV_INF);
	}

	/**
	 * INFCサービスの処理を指定<br>
	 * INFCは規格上個別通知のみとなる。<br>
	 * リモートIP : ＜パラメータ指定＞ <br>
	 * ESV : INFC<br>
	 * DEOJ : NodeProfileオブジェクト（一般ノード）<br>
	 * SEOJ : this(=ローカルノードのEOJ)<br>
	 * <br>
	 * 自身thisがローカルノードのEOJ(SEOJ)である前提<br>
	 * <br>
	 * @param remoteIpAddress 宛先のIPアドレス
	 * @return ControllerClass.ElInformPropsオブジェクト
	 */
	public ControllerClass.ElInformProps infC(String remoteIpAddress){
		return infC(remoteIpAddress, new NodeProfileClass());// INSTANCE_CODE_GENERAL
	}

	/**
	 * INFCサービスの処理を指定<br>
	 * INFCは規格上個別通知のみとなる。<br>
	 * リモートIP : ＜パラメータ指定＞ <br>
	 * ESV : INFC<br>
	 * DEOJ : ＜パラメータ指定＞<br>
	 * SEOJ : this(=ローカルノードのEOJ)<br>
	 * <br>
	 * 自身thisがローカルノードのEOJ(SEOJ)である前提<br>
	 * <br>
	 * @param remoteIpAddress 宛先のIPアドレス
	 * @param deoj 宛先EOJ
	 * @return ControllerClass.ElInformPropsオブジェクト
	 */
	@Override
	public ControllerClass.ElInformProps infC(String remoteIpAddress, ElClassBase deoj){
		return new ElInformProps(getElProcess(), remoteIpAddress, this, deoj, ElFrame.ESV_INFC);
	}


	/**
	 * 応答、通知系フレームの受信時の処理用クラス
	 */
	public static class ReportProcessor extends ElClassBase.ReportProcessor{

		/**
		 * ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "ControllerClass.ReportProcessor";

		/**
		 * {@inheritDoc}
		 */
		@Override
		protected void onReceivePropertyReportOfSet(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
			super.onReceivePropertyReportOfSet(seoj,  strTid,  strEsv,  objProp,  isSuccess);
			switch(objProp.getStrEpc()) {
				case EPC_OPERATION_STATUS:
					onSetOperationStatus(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_INDEX:
					onSetIndex(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				default:
					return;
			}
		}
		/**
		 * {@inheritDoc}
		 */
		@Override
		protected void onReceivePropertyReportOfGetOrInform(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
			super.onReceivePropertyReportOfGetOrInform(seoj,  strTid,  strEsv,  objProp,  isSuccess);
			switch(objProp.getStrEpc()) {
				case EPC_OPERATION_STATUS:
					onGetOperationStatus(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_CONTROLLER_ID:
					onGetControllerId(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MANAGED_DEVICE_NUM:
					onGetManagedDeviceNum(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_INDEX:
					onGetIndex(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_DEVICE_ID:
					onGetDeviceId(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_DEVICE_TYPE:
					onGetDeviceType(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_DEVICE_NAME:
					onGetDeviceName(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_CONNECT_STATE:
					onGetConnectState(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_BUSINESS_CODE:
					onGetBusinessCode(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_PRODUCT_CODE:
					onGetProductCode(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MANUFACTURED_YMD:
					onGetManufacturedYmd(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_REGISTERED_YMD:
					onGetRegisteredYmd(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_REGISTERED_INFO_RENEWAL_VERSION:
					onGetRegisteredInfoRenewalVersion(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_PLACE_TO_INSTALL:
					onGetPlaceToInstall(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_FAULT_STATUS:
					onGetFaultStatus(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_ADDRESS_OF_INSTALLATION_LOCATION:
					onGetAddressOfInstallationLocation(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_SET_PROPERTY_MAP:
					onGetSetPropertyMap(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_GET_PROPERTY_MAP:
					onGetGetPropertyMap(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				default:
					return;
			}
		}
		/**
		 * {@inheritDoc}
		 */
		@Override
		protected void onReceivePropertyReportOfInfC(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {
			super.onReceivePropertyReportOfInfC(seoj,  strTid,  strEsv,  objProp);
			switch(objProp.getStrEpc()) {
			case EPC_OPERATION_STATUS:
				onInformOperationStatus(seoj, strTid, strEsv, objProp);
				return;
			case EPC_CONTROLLER_ID:
				onInformControllerId(seoj, strTid, strEsv, objProp);
				return;
			case EPC_MANAGED_DEVICE_NUM:
				onInformManagedDeviceNum(seoj, strTid, strEsv, objProp);
				return;
			case EPC_INDEX:
				onInformIndex(seoj, strTid, strEsv, objProp);
				return;
			case EPC_DEVICE_ID:
				onInformDeviceId(seoj, strTid, strEsv, objProp);
				return;
			case EPC_DEVICE_TYPE:
				onInformDeviceType(seoj, strTid, strEsv, objProp);
				return;
			case EPC_DEVICE_NAME:
				onInformDeviceName(seoj, strTid, strEsv, objProp);
				return;
			case EPC_CONNECT_STATE:
				onInformConnectState(seoj, strTid, strEsv, objProp);
				return;
			case EPC_BUSINESS_CODE:
				onInformBusinessCode(seoj, strTid, strEsv, objProp);
				return;
			case EPC_PRODUCT_CODE:
				onInformProductCode(seoj, strTid, strEsv, objProp);
				return;
			case EPC_MANUFACTURED_YMD:
				onInformManufacturedYmd(seoj, strTid, strEsv, objProp);
				return;
			case EPC_REGISTERED_YMD:
				onInformRegisteredYmd(seoj, strTid, strEsv, objProp);
				return;
			case EPC_REGISTERED_INFO_RENEWAL_VERSION:
				onInformRegisteredInfoRenewalVersion(seoj, strTid, strEsv, objProp);
				return;
			case EPC_PLACE_TO_INSTALL:
				onInformPlaceToInstall(seoj, strTid, strEsv, objProp);
				return;
			case EPC_FAULT_STATUS:
				onInformFaultStatus(seoj, strTid, strEsv, objProp);
				return;
			case EPC_ADDRESS_OF_INSTALLATION_LOCATION:
				onInformAddressOfInstallationLocation(seoj, strTid, strEsv, objProp);
				return;
			case EPC_SET_PROPERTY_MAP:
				onInformSetPropertyMap(seoj, strTid, strEsv, objProp);
				return;
			case EPC_GET_PROPERTY_MAP:
				onInformGetPropertyMap(seoj, strTid, strEsv, objProp);
				return;

			default:
				return;
			}
		}

		/**
		 * Set関連
		 */

		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * 動作状態<br>
		 * EPC                 : 0x80<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ON／OFF の状態を示す。<br>
		 * ＯＮ＝0x30，ＯＦＦ＝0x31<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetOperationStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Set系）<br>
		 * インデックス<br>
		 * EPC                 : 0xC2<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器リストのインデックスを示す<br>
		 * 0x0001～0xFFFD (1～65533)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onSetIndex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

		/**
		 * Get,Inf関連
		 */

		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 動作状態<br>
		 * EPC                 : 0x80<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ON／OFF の状態を示す。<br>
		 * ＯＮ＝0x30，ＯＦＦ＝0x31<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetOperationStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * コントローラID<br>
		 * EPC                 : 0xC0<br>
		 * データタイプ        : unsigned char x 40<br>
		 * サイズ              : Max 40 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
		 * <br>
		 * コントローラのID を示す<br>
		 * 最大40 バイトのバイナリ値<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetControllerId(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 管理台数<br>
		 * EPC                 : 0xC1<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
		 * <br>
		 * 該当するコントローラID のコントローラが管理する機器の台数<br>
		 * 0x0000～0xFFFD (0～65533)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetManagedDeviceNum(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * インデックス<br>
		 * EPC                 : 0xC2<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器リストのインデックスを示す<br>
		 * 0x0001～0xFFFD (1～65533)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetIndex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 機器ID<br>
		 * EPC                 : 0xC3<br>
		 * データタイプ        : unsigned char x 40<br>
		 * サイズ              : Max 40 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
		 * <br>
		 * 機器のID を示す<br>
		 * 最大40 バイトのバイナリ値<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetDeviceId(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 機種<br>
		 * EPC                 : 0xC4<br>
		 * データタイプ        : unsigned char x 2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
		 * <br>
		 * 機器ID が示す機器の機種を示す。<br>
		 * クラスグループコード＋クラスコード<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetDeviceType(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 名称<br>
		 * EPC                 : 0xC5<br>
		 * データタイプ        : unsigned char x 64<br>
		 * サイズ              : Max 64 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器ID が示す機器の名称を示す。<br>
		 * UTF-8 (BOM 無し)で指定<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetDeviceName(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 接続状態<br>
		 * EPC                 : 0xC6<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
		 * <br>
		 * 機器ID が示す機器の状態を示す。<br>
		 * 接続中 = 0x41, 離脱中 =0x42, 未登録 = 0x43、削除=0x44<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetConnectState(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 管理対象機器事業者コード<br>
		 * EPC                 : 0xC7<br>
		 * データタイプ        : unsigned char x 3<br>
		 * サイズ              : 3 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
		 * <br>
		 * 3 バイトで指定。<br>
		 * (ECHONET ｺﾝｿｰｼｱﾑで規定。)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetBusinessCode(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 管理対象機器商品コード<br>
		 * EPC                 : 0xC8<br>
		 * データタイプ        : unsigned char x 12<br>
		 * サイズ              : Max 12 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ASCII コードで指定。<br>
		 * （各メーカ毎に規定。）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetProductCode(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 管理対象機器製造年月日<br>
		 * EPC                 : 0xC9<br>
		 * データタイプ        : unsigned char x 4<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 4 バイトで指定。<br>
		 * YYMD(1 文字1 ﾊﾞｲﾄ)で示す。<br>
		 * YY ： 西暦年(1999 年の場合:0x07CF)<br>
		 * M：月(12 月の場合＝0x0C)<br>
		 * D：日(20 日の場合＝0x14)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetManufacturedYmd(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 管理対象機器登録情報更新年月日<br>
		 * EPC                 : 0xCA<br>
		 * データタイプ        : unsigned char x 4<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 4 バイトで指定。<br>
		 * YYMD(1 文字1 ﾊﾞｲﾄ)で示す。<br>
		 * YY ： 西暦年(1999 年の場合:0x07CF)<br>
		 * M：月(12 月の場合＝0x0C)<br>
		 * D：日(20 日の場合＝0x14)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetRegisteredYmd(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 管理対象機器登録情報更新バージョン情報<br>
		 * EPC                 : 0xCB<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 管理する機器が追加、削除されるたびに、更新するバージョン情報<br>
		 * 0x0000～0xFFFD (0～65533)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetRegisteredInfoRenewalVersion(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 管理対象機器設置場所<br>
		 * EPC                 : 0xCC<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器ID が示す機器の設置場所を示す<br>
		 * 2.2 設置場所プロパティを参照<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetPlaceToInstall(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 管理対象機器異常発生状態<br>
		 * EPC                 : 0xCD<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器ID が示す機器の異常発生状態を示す<br>
		 * 異常発生有0x41、異常発生無0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetFaultStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 設置住所<br>
		 * EPC                 : 0xE0<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : Max.255 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * コントローラが設置されている場所の住所を示す。<br>
		 * UTF-8 (BOM 無し)で指定<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetAddressOfInstallationLocation(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 管理対象機器Setプロパティマップ<br>
		 * EPC                 : 0xCE<br>
		 * データタイプ        : unsigned char x (Max. 17)<br>
		 * サイズ              : Max.17 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器ID が示す機器のSet プロパティマップを示す。<br>
		 * 付録１を参照のこと<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetSetPropertyMap(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 管理対象機器Getプロパティマップ<br>
		 * EPC                 : 0xCF<br>
		 * データタイプ        : unsigned char x (Max. 17) byte<br>
		 * サイズ              : Max.17 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器ID が示す機器のGet プロパティマップを示す。<br>
		 * 付録１を参照のこと<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetGetPropertyMap(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

		/**
		 * InfC関連
		 */

		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 動作状態<br>
		 * EPC                 : 0x80<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : オプション<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : 必須<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ON／OFF の状態を示す。<br>
		 * ＯＮ＝0x30，ＯＦＦ＝0x31<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformOperationStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * コントローラID<br>
		 * EPC                 : 0xC0<br>
		 * データタイプ        : unsigned char x 40<br>
		 * サイズ              : Max 40 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
		 * <br>
		 * コントローラのID を示す<br>
		 * 最大40 バイトのバイナリ値<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformControllerId(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 管理台数<br>
		 * EPC                 : 0xC1<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
		 * <br>
		 * 該当するコントローラID のコントローラが管理する機器の台数<br>
		 * 0x0000～0xFFFD (0～65533)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformManagedDeviceNum(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * インデックス<br>
		 * EPC                 : 0xC2<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器リストのインデックスを示す<br>
		 * 0x0001～0xFFFD (1～65533)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformIndex(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 機器ID<br>
		 * EPC                 : 0xC3<br>
		 * データタイプ        : unsigned char x 40<br>
		 * サイズ              : Max 40 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
		 * <br>
		 * 機器のID を示す<br>
		 * 最大40 バイトのバイナリ値<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformDeviceId(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 機種<br>
		 * EPC                 : 0xC4<br>
		 * データタイプ        : unsigned char x 2<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
		 * <br>
		 * 機器ID が示す機器の機種を示す。<br>
		 * クラスグループコード＋クラスコード<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformDeviceType(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 名称<br>
		 * EPC                 : 0xC5<br>
		 * データタイプ        : unsigned char x 64<br>
		 * サイズ              : Max 64 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器ID が示す機器の名称を示す。<br>
		 * UTF-8 (BOM 無し)で指定<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformDeviceName(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 接続状態<br>
		 * EPC                 : 0xC6<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
		 * <br>
		 * 機器ID が示す機器の状態を示す。<br>
		 * 接続中 = 0x41, 離脱中 =0x42, 未登録 = 0x43、削除=0x44<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformConnectState(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 管理対象機器事業者コード<br>
		 * EPC                 : 0xC7<br>
		 * データタイプ        : unsigned char x 3<br>
		 * サイズ              : 3 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
		 * <br>
		 * 3 バイトで指定。<br>
		 * (ECHONET ｺﾝｿｰｼｱﾑで規定。)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformBusinessCode(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 管理対象機器商品コード<br>
		 * EPC                 : 0xC8<br>
		 * データタイプ        : unsigned char x 12<br>
		 * サイズ              : Max 12 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * ASCII コードで指定。<br>
		 * （各メーカ毎に規定。）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformProductCode(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 管理対象機器製造年月日<br>
		 * EPC                 : 0xC9<br>
		 * データタイプ        : unsigned char x 4<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 4 バイトで指定。<br>
		 * YYMD(1 文字1 ﾊﾞｲﾄ)で示す。<br>
		 * YY ： 西暦年(1999 年の場合:0x07CF)<br>
		 * M：月(12 月の場合＝0x0C)<br>
		 * D：日(20 日の場合＝0x14)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformManufacturedYmd(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 管理対象機器登録情報更新年月日<br>
		 * EPC                 : 0xCA<br>
		 * データタイプ        : unsigned char x 4<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 4 バイトで指定。<br>
		 * YYMD(1 文字1 ﾊﾞｲﾄ)で示す。<br>
		 * YY ： 西暦年(1999 年の場合:0x07CF)<br>
		 * M：月(12 月の場合＝0x0C)<br>
		 * D：日(20 日の場合＝0x14)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformRegisteredYmd(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 管理対象機器登録情報更新バージョン情報<br>
		 * EPC                 : 0xCB<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 管理する機器が追加、削除されるたびに、更新するバージョン情報<br>
		 * 0x0000～0xFFFD (0～65533)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformRegisteredInfoRenewalVersion(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 管理対象機器設置場所<br>
		 * EPC                 : 0xCC<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器ID が示す機器の設置場所を示す<br>
		 * 2.2 設置場所プロパティを参照<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformPlaceToInstall(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 管理対象機器異常発生状態<br>
		 * EPC                 : 0xCD<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器ID が示す機器の異常発生状態を示す<br>
		 * 異常発生有0x41、異常発生無0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformFaultStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 設置住所<br>
		 * EPC                 : 0xE0<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : Max.255 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * コントローラが設置されている場所の住所を示す。<br>
		 * UTF-8 (BOM 無し)で指定<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformAddressOfInstallationLocation(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 管理対象機器Setプロパティマップ<br>
		 * EPC                 : 0xCE<br>
		 * データタイプ        : unsigned char x (Max. 17)<br>
		 * サイズ              : Max.17 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器ID が示す機器のSet プロパティマップを示す。<br>
		 * 付録１を参照のこと<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformSetPropertyMap(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 管理対象機器Getプロパティマップ<br>
		 * EPC                 : 0xCF<br>
		 * データタイプ        : unsigned char x (Max. 17) byte<br>
		 * サイズ              : Max.17 byte<br>
		 * 単位                : -<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 機器ID が示す機器のGet プロパティマップを示す。<br>
		 * 付録１を参照のこと<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformGetPropertyMap(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	protected synchronized String getEdtValueFromApp(String epc) {
		String retEdt =super.getEdtValueFromApp(epc);
		if(retEdt != null) {
			return retEdt;
		}
		switch(epc) {
			case EPC_OPERATION_STATUS:
				return this.getFromAppOperationStatus();
			case EPC_CONTROLLER_ID:
				return this.getFromAppControllerId();
			case EPC_MANAGED_DEVICE_NUM:
				return this.getFromAppManagedDeviceNum();
			case EPC_INDEX:
				return this.getFromAppIndex();
			case EPC_DEVICE_ID:
				return this.getFromAppDeviceId();
			case EPC_DEVICE_TYPE:
				return this.getFromAppDeviceType();
			case EPC_DEVICE_NAME:
				return this.getFromAppDeviceName();
			case EPC_CONNECT_STATE:
				return this.getFromAppConnectState();
			case EPC_BUSINESS_CODE:
				return this.getFromAppBusinessCode();
			case EPC_PRODUCT_CODE:
				return this.getFromAppProductCode();
			case EPC_MANUFACTURED_YMD:
				return this.getFromAppManufacturedYmd();
			case EPC_REGISTERED_YMD:
				return this.getFromAppRegisteredYmd();
			case EPC_REGISTERED_INFO_RENEWAL_VERSION:
				return this.getFromAppRegisteredInfoRenewalVersion();
			case EPC_PLACE_TO_INSTALL:
				return this.getFromAppPlaceToInstall();
			case EPC_FAULT_STATUS:
				return this.getFromAppFaultStatus();
			case EPC_ADDRESS_OF_INSTALLATION_LOCATION:
				return this.getFromAppAddressOfInstallationLocation();
			case EPC_SET_PROPERTY_MAP:
				return this.getFromAppSetPropertyMap();
			case EPC_GET_PROPERTY_MAP:
				return this.getFromAppGetPropertyMap();
			default:
				return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected synchronized boolean isValidEdtValue(ElProp prop) {
		boolean bResult =super.isValidEdtValue(prop);
		if(bResult == true) {
			return true;
		}
		String epc = prop.getStrEpc();
		String edt = prop.getStrEdt();
		switch (epc) {
			case EPC_OPERATION_STATUS:
				return this.isValidEdtOperationStatus(edt);
			case EPC_CONTROLLER_ID:
				return this.isValidEdtControllerId(edt);
			case EPC_MANAGED_DEVICE_NUM:
				return this.isValidEdtManagedDeviceNum(edt);
			case EPC_INDEX:
				return this.isValidEdtIndex(edt);
			case EPC_DEVICE_ID:
				return this.isValidEdtDeviceId(edt);
			case EPC_DEVICE_TYPE:
				return this.isValidEdtDeviceType(edt);
			case EPC_DEVICE_NAME:
				return this.isValidEdtDeviceName(edt);
			case EPC_CONNECT_STATE:
				return this.isValidEdtConnectState(edt);
			case EPC_BUSINESS_CODE:
				return this.isValidEdtBusinessCode(edt);
			case EPC_PRODUCT_CODE:
				return this.isValidEdtProductCode(edt);
			case EPC_MANUFACTURED_YMD:
				return this.isValidEdtManufacturedYmd(edt);
			case EPC_REGISTERED_YMD:
				return this.isValidEdtRegisteredYmd(edt);
			case EPC_REGISTERED_INFO_RENEWAL_VERSION:
				return this.isValidEdtRegisteredInfoRenewalVersion(edt);
			case EPC_PLACE_TO_INSTALL:
				return this.isValidEdtPlaceToInstall(edt);
			case EPC_FAULT_STATUS:
				return this.isValidEdtFaultStatus(edt);
			case EPC_ADDRESS_OF_INSTALLATION_LOCATION:
				return this.isValidEdtAddressOfInstallationLocation(edt);
			case EPC_SET_PROPERTY_MAP:
				return this.isValidEdtSetPropertyMap(edt);
			case EPC_GET_PROPERTY_MAP:
				return this.isValidEdtGetPropertyMap(edt);
			default:
				return false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected synchronized boolean setEdtValueToApp(ElProp prop) {
		boolean bResult =super.setEdtValueToApp(prop);
		if(bResult == true) {
			return true;
		}
		String epc = prop.getStrEpc();
		String edt = prop.getStrEdt();
		switch (epc) {
			case EPC_OPERATION_STATUS:
				return this.setToAppOperationStatus(edt);
			case EPC_INDEX:
				return this.setToAppIndex(edt);
			default:
				return false;
		}
	}

	 // アプリケーションより値を取得するメソッド群
	/**
	 * 動作状態<br>
	 * EPC                 : 0x80<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * ON／OFF の状態を示す。<br>
	 * ＯＮ＝0x30，ＯＦＦ＝0x31<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */

//	protected String getFromAppOperationStatus(){return null;}
	/**
	 * コントローラID<br>
	 * EPC                 : 0xC0<br>
	 * データタイプ        : unsigned char x 40<br>
	 * サイズ              : Max 40 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
	 * <br>
	 * コントローラのID を示す<br>
	 * 最大40 バイトのバイナリ値<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppControllerId(){return null;}
	/**
	 * 管理台数<br>
	 * EPC                 : 0xC1<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
	 * <br>
	 * 該当するコントローラID のコントローラが管理する機器の台数<br>
	 * 0x0000～0xFFFD (0～65533)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppManagedDeviceNum(){return null;}
	/**
	 * インデックス<br>
	 * EPC                 : 0xC2<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 機器リストのインデックスを示す<br>
	 * 0x0001～0xFFFD (1～65533)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppIndex(){return null;}
	/**
	 * 機器ID<br>
	 * EPC                 : 0xC3<br>
	 * データタイプ        : unsigned char x 40<br>
	 * サイズ              : Max 40 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
	 * <br>
	 * 機器のID を示す<br>
	 * 最大40 バイトのバイナリ値<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppDeviceId(){return null;}
	/**
	 * 機種<br>
	 * EPC                 : 0xC4<br>
	 * データタイプ        : unsigned char x 2<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
	 * <br>
	 * 機器ID が示す機器の機種を示す。<br>
	 * クラスグループコード＋クラスコード<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppDeviceType(){return null;}
	/**
	 * 名称<br>
	 * EPC                 : 0xC5<br>
	 * データタイプ        : unsigned char x 64<br>
	 * サイズ              : Max 64 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 機器ID が示す機器の名称を示す。<br>
	 * UTF-8 (BOM 無し)で指定<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppDeviceName(){return null;}
	/**
	 * 接続状態<br>
	 * EPC                 : 0xC6<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
	 * <br>
	 * 機器ID が示す機器の状態を示す。<br>
	 * 接続中 = 0x41, 離脱中 =0x42, 未登録 = 0x43、削除=0x44<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppConnectState(){return null;}
	/**
	 * 管理対象機器事業者コード<br>
	 * EPC                 : 0xC7<br>
	 * データタイプ        : unsigned char x 3<br>
	 * サイズ              : 3 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
	 * <br>
	 * 3 バイトで指定。<br>
	 * (ECHONET ｺﾝｿｰｼｱﾑで規定。)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppBusinessCode(){return null;}
	/**
	 * 管理対象機器商品コード<br>
	 * EPC                 : 0xC8<br>
	 * データタイプ        : unsigned char x 12<br>
	 * サイズ              : Max 12 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * ASCII コードで指定。<br>
	 * （各メーカ毎に規定。）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
//	protected String getFromAppProductCode(){return null;}
	/**
	 * 管理対象機器製造年月日<br>
	 * EPC                 : 0xC9<br>
	 * データタイプ        : unsigned char x 4<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 4 バイトで指定。<br>
	 * YYMD(1 文字1 ﾊﾞｲﾄ)で示す。<br>
	 * YY ： 西暦年(1999 年の場合:0x07CF)<br>
	 * M：月(12 月の場合＝0x0C)<br>
	 * D：日(20 日の場合＝0x14)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppManufacturedYmd(){return null;}
	/**
	 * 管理対象機器登録情報更新年月日<br>
	 * EPC                 : 0xCA<br>
	 * データタイプ        : unsigned char x 4<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 4 バイトで指定。<br>
	 * YYMD(1 文字1 ﾊﾞｲﾄ)で示す。<br>
	 * YY ： 西暦年(1999 年の場合:0x07CF)<br>
	 * M：月(12 月の場合＝0x0C)<br>
	 * D：日(20 日の場合＝0x14)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppRegisteredYmd(){return null;}
	/**
	 * 管理対象機器登録情報更新バージョン情報<br>
	 * EPC                 : 0xCB<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 管理する機器が追加、削除されるたびに、更新するバージョン情報<br>
	 * 0x0000～0xFFFD (0～65533)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppRegisteredInfoRenewalVersion(){return null;}
	/**
	 * 管理対象機器設置場所<br>
	 * EPC                 : 0xCC<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 機器ID が示す機器の設置場所を示す<br>
	 * 2.2 設置場所プロパティを参照<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppPlaceToInstall(){return null;}
	/**
	 * 管理対象機器異常発生状態<br>
	 * EPC                 : 0xCD<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 機器ID が示す機器の異常発生状態を示す<br>
	 * 異常発生有0x41、異常発生無0x42<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppFaultStatus(){return null;}
	/**
	 * 設置住所<br>
	 * EPC                 : 0xE0<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : Max.255 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * コントローラが設置されている場所の住所を示す。<br>
	 * UTF-8 (BOM 無し)で指定<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppAddressOfInstallationLocation(){return null;}
	/**
	 * 管理対象機器Setプロパティマップ<br>
	 * EPC                 : 0xCE<br>
	 * データタイプ        : unsigned char x (Max. 17)<br>
	 * サイズ              : Max.17 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 機器ID が示す機器のSet プロパティマップを示す。<br>
	 * 付録１を参照のこと<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppSetPropertyMap(){return null;}
	/**
	 * 管理対象機器Getプロパティマップ<br>
	 * EPC                 : 0xCF<br>
	 * データタイプ        : unsigned char x (Max. 17) byte<br>
	 * サイズ              : Max.17 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 機器ID が示す機器のGet プロパティマップを示す。<br>
	 * 付録１を参照のこと<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppGetPropertyMap(){return null;}

	// アプリケーションから取得した値の妥当性を検証するメソッド群
	/**
	 * 動作状態<br>
	 * EPC                 : 0x80<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * ON／OFF の状態を示す。<br>
	 * ＯＮ＝0x30，ＯＦＦ＝0x31<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
//	protected boolean isValidEdtOperationStatus(String edt){return false;}
	/**
	 * コントローラID<br>
	 * EPC                 : 0xC0<br>
	 * データタイプ        : unsigned char x 40<br>
	 * サイズ              : Max 40 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
	 * <br>
	 * コントローラのID を示す<br>
	 * 最大40 バイトのバイナリ値<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtControllerId(String edt){
		if(edt == null || !(edt.length() <= 40 * 2)) return false;
		return true;
	}
	/**
	 * 管理台数<br>
	 * EPC                 : 0xC1<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
	 * <br>
	 * 該当するコントローラID のコントローラが管理する機器の台数<br>
	 * 0x0000～0xFFFD (0～65533)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtManagedDeviceNum(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		return true;
	}
	/**
	 * インデックス<br>
	 * EPC                 : 0xC2<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 機器リストのインデックスを示す<br>
	 * 0x0001～0xFFFD (1～65533)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtIndex(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		return true;
	}
	/**
	 * 機器ID<br>
	 * EPC                 : 0xC3<br>
	 * データタイプ        : unsigned char x 40<br>
	 * サイズ              : Max 40 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
	 * <br>
	 * 機器のID を示す<br>
	 * 最大40 バイトのバイナリ値<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtDeviceId(String edt){
		if(edt == null || !(edt.length() <= 40 * 2)) return false;
		return true;
	}
	/**
	 * 機種<br>
	 * EPC                 : 0xC4<br>
	 * データタイプ        : unsigned char x 2<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
	 * <br>
	 * 機器ID が示す機器の機種を示す。<br>
	 * クラスグループコード＋クラスコード<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtDeviceType(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		return true;
	}
	/**
	 * 名称<br>
	 * EPC                 : 0xC5<br>
	 * データタイプ        : unsigned char x 64<br>
	 * サイズ              : Max 64 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 機器ID が示す機器の名称を示す。<br>
	 * UTF-8 (BOM 無し)で指定<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtDeviceName(String edt){
		if(edt == null || !(edt.length() <= 64 * 2)) return false;
		return true;
	}
	/**
	 * 接続状態<br>
	 * EPC                 : 0xC6<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
	 * <br>
	 * 機器ID が示す機器の状態を示す。<br>
	 * 接続中 = 0x41, 離脱中 =0x42, 未登録 = 0x43、削除=0x44<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtConnectState(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_CONNECTED)
				&& ! edt.equalsIgnoreCase(EDT_DISCONNECTED)
				&& ! edt.equalsIgnoreCase(EDT_NOT_REGISTERD)
				&& ! edt.equalsIgnoreCase(EDT_DELETED)
				) return false;
		return true;
	}
	/**
	 * 管理対象機器事業者コード<br>
	 * EPC                 : 0xC7<br>
	 * データタイプ        : unsigned char x 3<br>
	 * サイズ              : 3 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : ※）他のシステムと連携する場合は本プロパティに該当するデータを必ず保持すること。ただし、ECHONET Lite ドメイン内でこのデータを共有させないコントローラは、該当するプロパティをコントローラクラスに搭載する必要は無い。<br>
	 * <br>
	 * 3 バイトで指定。<br>
	 * (ECHONET ｺﾝｿｰｼｱﾑで規定。)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtBusinessCode(String edt){
		if(edt == null || !(edt.length() == 3 * 2)) return false;
		return true;
	}
	/**
	 * 管理対象機器商品コード<br>
	 * EPC                 : 0xC8<br>
	 * データタイプ        : unsigned char x 12<br>
	 * サイズ              : Max 12 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * ASCII コードで指定。<br>
	 * （各メーカ毎に規定。）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
//	protected boolean isValidEdtProductCode(String edt){return false;}
	/**
	 * 管理対象機器製造年月日<br>
	 * EPC                 : 0xC9<br>
	 * データタイプ        : unsigned char x 4<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 4 バイトで指定。<br>
	 * YYMD(1 文字1 ﾊﾞｲﾄ)で示す。<br>
	 * YY ： 西暦年(1999 年の場合:0x07CF)<br>
	 * M：月(12 月の場合＝0x0C)<br>
	 * D：日(20 日の場合＝0x14)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtManufacturedYmd(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! ElUtil.checkEdtYmdFormat(edt)) return false;
		return true;
	}
	/**
	 * 管理対象機器登録情報更新年月日<br>
	 * EPC                 : 0xCA<br>
	 * データタイプ        : unsigned char x 4<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 4 バイトで指定。<br>
	 * YYMD(1 文字1 ﾊﾞｲﾄ)で示す。<br>
	 * YY ： 西暦年(1999 年の場合:0x07CF)<br>
	 * M：月(12 月の場合＝0x0C)<br>
	 * D：日(20 日の場合＝0x14)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtRegisteredYmd(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! ElUtil.checkEdtYmdFormat(edt)) return false;
		return true;
	}
	/**
	 * 管理対象機器登録情報更新バージョン情報<br>
	 * EPC                 : 0xCB<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 管理する機器が追加、削除されるたびに、更新するバージョン情報<br>
	 * 0x0000～0xFFFD (0～65533)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtRegisteredInfoRenewalVersion(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		return true;
	}
	/**
	 * 管理対象機器設置場所<br>
	 * EPC                 : 0xCC<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 機器ID が示す機器の設置場所を示す<br>
	 * 2.2 設置場所プロパティを参照<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtPlaceToInstall(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		return true;
	}
	/**
	 * 管理対象機器異常発生状態<br>
	 * EPC                 : 0xCD<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 機器ID が示す機器の異常発生状態を示す<br>
	 * 異常発生有0x41、異常発生無0x42<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtFaultStatus(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_ABNORMAL_STATE_TRUE) && ! edt.equalsIgnoreCase(EDT_ABNORMAL_STATE_FALSE)) return false;
		return true;
	}
	/**
	 * 設置住所<br>
	 * EPC                 : 0xE0<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : Max.255 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * コントローラが設置されている場所の住所を示す。<br>
	 * UTF-8 (BOM 無し)で指定<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtAddressOfInstallationLocation(String edt){
		if(edt == null || !(edt.length() <= 255 * 2)) return false;
		return true;
	}
	/**
	 * 管理対象機器Setプロパティマップ<br>
	 * EPC                 : 0xCE<br>
	 * データタイプ        : unsigned char x (Max. 17)<br>
	 * サイズ              : Max.17 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 機器ID が示す機器のSet プロパティマップを示す。<br>
	 * 付録１を参照のこと<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtSetPropertyMap(String edt){
		if(edt == null || !(edt.length() <= 17 * 2)) return false;
		return true;
	}
	/**
	 * 管理対象機器Getプロパティマップ<br>
	 * EPC                 : 0xCF<br>
	 * データタイプ        : unsigned char x (Max. 17) byte<br>
	 * サイズ              : Max.17 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 機器ID が示す機器のGet プロパティマップを示す。<br>
	 * 付録１を参照のこと<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtGetPropertyMap(String edt){
		if(edt == null || !(edt.length() <= 17 * 2)) return false;
		return true;
	}

	// アプリケーションの設置する値の妥当性を検証するメソッド群
	/**
	 * 動作状態<br>
	 * EPC                 : 0x80<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : オプション<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : 必須<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * ON／OFF の状態を示す。<br>
	 * ＯＮ＝0x30，ＯＦＦ＝0x31<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
//	protected boolean setToAppOperationStatus(String edt){return false;}
	/**
	 * インデックス<br>
	 * EPC                 : 0xC2<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : -<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 機器リストのインデックスを示す<br>
	 * 0x0001～0xFFFD (1～65533)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Success , false Failure
	 */
	protected boolean setToAppIndex(String edt){return false;}


	/**
	 * EPC名称マップ
	 */
	public static Map<String, String> MAP_EPC_NAME = new HashMap<String, String>() {
		{
			put("80", "動作状態");
			put("C0", "コントローラID");
			put("C1", "管理台数");
			put("C2", "インデックス");
			put("C3", "機器ID");
			put("C4", "機種");
			put("C5", "名称");
			put("C6", "接続状態");
			put("C7", "管理対象機器事業者コード");
			put("C8", "管理対象機器商品コード");
			put("C9", "管理対象機器製造年月日");
			put("CA", "管理対象機器登録情報更新年月日");
			put("CB", "管理対象機器登録情報更新バージョン情報");
			put("CC", "管理対象機器設置場所");
			put("CD", "管理対象機器異常発生状態");
			put("E0", "設置住所");
			put("CE", "管理対象機器Setプロパティマップ");
			put("CF", "管理対象機器Getプロパティマップ");
		}
	};

}
