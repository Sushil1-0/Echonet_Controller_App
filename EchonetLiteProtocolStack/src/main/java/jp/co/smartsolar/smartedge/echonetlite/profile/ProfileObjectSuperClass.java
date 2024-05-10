package jp.co.smartsolar.smartedge.echonetlite.profile;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jp.co.smartsolar.smartedge.echonetlite.ElClassBase;
import jp.co.smartsolar.smartedge.echonetlite.ElProcess;
import jp.co.smartsolar.smartedge.echonetlite.ElProp;
import jp.co.smartsolar.smartedge.echonetlite.ElUtil;

/**
 * プロファイルオブジェクトスーパークラス
 */
public class ProfileObjectSuperClass extends ElClassBase {

	/**
	 * ログ出力用タグ
	 */
	@SuppressWarnings("unused")
	private static final String TAG = "ProfileObjectSuperClass";

	/**
	 * EPC : 異常発生状態
	 * 何等かの異常発生状態を示す。
	 */
	public static final String EPC_ERROR_STATUS = "88";
	/**
	 * EDT :  異常発生有
	 */
	public static final String EDT_ERROR_STATUS_TRUE = "41";
	/**
	 *  EDT : 異常発生無
	 */
	public static final String EDT_ERROR_STATUS_FALSE = "42";

	/**
	 * EPC : メーカーコード
	 * 3バイトで指定。
	 */
	public static final String EPC_MAKER_CODE = "8A";

	/**
	 *EPC :  事業場コード
	 * 3バイトの事業場コードで指定。
	 */
	public static final String EPC_PLANT_CODE = "8B";

	/**
	 * EPC : 商品コード
	 * ASCIIコードで指定。
	 */
	public static final String EPC_PRODUCT_CODE = "8C";

	/**
	 * EPC : 製造番号
	 * ASCIIコードで指定。
	 */
	public static final String EPC_SERIAL_NUMBER = "8D";

	/**
	 * EPC : 製造年月日
	 * 4バイトで指定。
	 */
	public static final String EPC_MADE_YMD = "8E";

	/**
	 * EPC : 状変アナウンスプロパティマップ
	 */
	public static final String EPC_CONV_ANNOUNCE_PROP_MAP = "9D";

	/**
	 * EPC : Setプロパティマップ
	 */
	public static final String EPC_SET_PROP_MAP = "9E";

	/**
	 * EPC : Getプロパティマップ
	 */
	public static final String EPC_GET_PROP_MAP = "9F";


	/**
	 * コンストラクタ
	 */
	public ProfileObjectSuperClass() {
		super();
	}
	/**
	 * コンストラクタ
	 * @param classGroupCode クラスグループコード（16進数文字列）
	 * @param classCode クラスコード（16進数文字列）
	 */
	public ProfileObjectSuperClass( String classGroupCode, String classCode  ) {
		this();
		this.setStrClassGroupCode(classGroupCode);
		this.setStrClassCode(classCode);
	}
	/**
	 * コンストラクタ
	 * @param classGroupCode クラスグループコード（16進数文字列）
	 * @param classCode クラスコード（16進数文字列）
	 * @param entityCode エンティティコード（16進数文字列）
	 */
	public ProfileObjectSuperClass( String classGroupCode, String classCode, String entityCode  ) {
		this(classGroupCode, classCode);
		this.setStrEntityCode(entityCode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initPorpMaps(){
		super.initPorpMaps();

//		this.addMapGetProps(EPC_ERROR_STATUS); // 必須ではない
		this.addMapGetProps(EPC_MAKER_CODE);
//		this.addMapGetProps(EPC_PLANT_CODE); // 必須ではない
		this.addMapGetProps(EPC_PRODUCT_CODE); // 必須ではない
//		this.addMapGetProps(EPC_SERIAL_NUMBER); // 必須ではない
//		this.addMapGetProps(EPC_MADE_YMD); // 必須ではない
		this.addMapGetProps(EPC_CONV_ANNOUNCE_PROP_MAP);
		this.addMapGetProps(EPC_SET_PROP_MAP);
		this.addMapGetProps(EPC_GET_PROP_MAP);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onNewEojFound(){
		super.onNewEojFound();
		getElProcess().getElBulkEventProcessor().onNewProfileObjectFound(this);
	}


	/**
	 * Set系処理の集約クラス
	 * 0x60 : プロパティ値書込み要求（応答不要）
	 * 0x61 : プロパティ値書込み要求（応答要）
	 */
	public static class ElSetProps extends ElClassBase.ElSetProps{

		/**
		 * ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "ProfileObjectSuperClass.ElSetProps";

		/**
		 * {@inheritDoc}
		 */
		public ElSetProps(ElProcess elProcess, String remoteHostName, ElClassBase objSeoj, ElClassBase objDeoj, String strEsv) {
			super(elProcess, remoteHostName, objSeoj, objDeoj, strEsv);
		}
	}

	/**
	 * Get系処理の集約クラス
	 * 0x62 : プロパティ値読出し要求
	 */
	public static class ElGetProps extends ElClassBase.ElGetProps {

		/**
		 * ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "ProfileObjectSuperClass.ElGetProps";

		/**
		 * {@inheritDoc}
		 */
		public ElGetProps(ElProcess elProcess, String remoteHostName, ElClassBase objSeoj, ElClassBase objDeoj, String strEsv) {
			super(elProcess, remoteHostName, objSeoj, objDeoj, strEsv);
		}

		/**
		 * 異常発生状態<br>
		 * EPC                 : 0x88<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 何らかの異常の発生状況を示す。<br>
		 * 異常発生有＝0x41、異常発生無＝0x42<br>
		 * <br>
		 * @return ProfileObjectSuperClass.ElGetPropsオブジェクト
		 */
		public ProfileObjectSuperClass.ElGetProps reqGetErrorStatus(){
			listProperty.add(new ElProp(EPC_ERROR_STATUS));
			return this;
		}
		/**
		 * メーカコード<br>
		 * EPC                 : 0x8A<br>
		 * データタイプ        : unsigned char×3<br>
		 * サイズ              : 3 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * ３バイトで指定。<br>
		 * (ECHONET コンソーシアムで規定。)<br>
		 * <br>
		 * @return ProfileObjectSuperClass.ElGetPropsオブジェクト
		 */
		public ProfileObjectSuperClass.ElGetProps reqGetMakerCode(){
			listProperty.add(new ElProp(EPC_MAKER_CODE));
			return this;
		}
		/**
		 * 事業場コード<br>
		 * EPC                 : 0x8B<br>
		 * データタイプ        : unsigned char×3<br>
		 * サイズ              : 3 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 3バイトの事業場コードで指定。<br>
		 * （各メーカ毎に規定。）<br>
		 * <br>
		 * @return ProfileObjectSuperClass.ElGetPropsオブジェクト
		 */
		public ProfileObjectSuperClass.ElGetProps reqGetPlantCode(){
			listProperty.add(new ElProp(EPC_PLANT_CODE));
			return this;
		}
		/**
		 * 商品コード<br>
		 * EPC                 : 0x8C<br>
		 * データタイプ        : unsigned char×12<br>
		 * サイズ              : 12 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * ASCIIコードで指定。<br>
		 * （各メーカ毎に規定。）<br>
		 * <br>
		 * @return ProfileObjectSuperClass.ElGetPropsオブジェクト
		 */
		public ProfileObjectSuperClass.ElGetProps reqGetProductCode(){
			listProperty.add(new ElProp(EPC_PRODUCT_CODE));
			return this;
		}
		/**
		 * 製造番号<br>
		 * EPC                 : 0x8D<br>
		 * データタイプ        : unsigned char ×12<br>
		 * サイズ              : 12 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * ASCIIコードで指定。<br>
		 * （各メーカ毎に規定。）<br>
		 * <br>
		 * @return ProfileObjectSuperClass.ElGetPropsオブジェクト
		 */
		public ProfileObjectSuperClass.ElGetProps reqGetSerialNumber(){
			listProperty.add(new ElProp(EPC_SERIAL_NUMBER));
			return this;
		}
		/**
		 * 製造年月日<br>
		 * EPC                 : 0x8E<br>
		 * データタイプ        : unsigned char×4<br>
		 * サイズ              : 4 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 4バイトで指定。<br>
		 * YYMD (1文字 1ﾊﾞｲﾄ )で示す。<br>
		 * YY ：西暦年 (1999 年の場 合:0x07 CF )<br>
		 * M：月 (12 月の場合＝ 0x0C)<br>
		 * D：日 (20 日の場合＝ 0x14 )<br>
		 * <br>
		 * @return ProfileObjectSuperClass.ElGetPropsオブジェクト
		 */
		public ProfileObjectSuperClass.ElGetProps reqGetMadeYmd(){
			listProperty.add(new ElProp(EPC_MADE_YMD));
			return this;
		}
		/**
		 * 状変アナウンスプロパティマップ<br>
		 * EPC                 : 0x9D<br>
		 * データタイプ        : unsigned char×(MAX17)<br>
		 * サイズ              : Max. 17 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 *「APPENDIX ECHONET機器オブジェクト詳細規定」<br>
		 * 付録 1. 参照<br>
		 * <br>
		 * @return ProfileObjectSuperClass.ElGetPropsオブジェクト
		 */
		public ProfileObjectSuperClass.ElGetProps reqGetConvAnnouncePropMap(){
			listProperty.add(new ElProp(EPC_CONV_ANNOUNCE_PROP_MAP));
			return this;
		}
		/**
		 * Setプロパティマップ<br>
		 * EPC                 : 0x9E<br>
		 * データタイプ        : unsigned char×(MAX17)<br>
		 * サイズ              : Max. 17 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 *「APPENDIX ECHONET機器オブジェクト詳細規定」<br>
		 * 付録 1. 参照<br>
		 * <br>
		 * @return ProfileObjectSuperClass.ElGetPropsオブジェクト
		 */
		public ProfileObjectSuperClass.ElGetProps reqGetSetPropMap(){
			listProperty.add(new ElProp(EPC_SET_PROP_MAP));
			return this;
		}
		/**
		 * Getプロパティマップ<br>
		 * EPC                 : 0x9F<br>
		 * データタイプ        : unsigned char×(MAX17)<br>
		 * サイズ              : Max. 17 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 *「APPENDIX ECHONET機器オブジェクト詳細規定」<br>
		 * 付録 1. 参照<br>
		 * <br>
		 * @return ProfileObjectSuperClass.ElGetPropsオブジェクト
		 */
		public ProfileObjectSuperClass.ElGetProps reqGetGetPropMap(){
			listProperty.add(new ElProp(EPC_GET_PROP_MAP));
			return this;
		}

	}

	/**
	 * 通知系処理の集約クラス
 	 * 0x73 : プロパティ値通知
	 * 0x63 : プロパティ値通知要求
	 */
	public static class ElInformProps extends ElClassBase.ElInformProps {

		/**
		 * ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "ProfileObjectSuperClass.ElInformProps";

		/**
		 * {@inheritDoc}
		 */
		public ElInformProps(ElProcess elProcess, String remoteHostName, ElClassBase objSeoj, ElClassBase objDeoj, String strEsv) {
			super(elProcess, remoteHostName, objSeoj, objDeoj, strEsv);
		}

		/**
		 * 異常発生状態<br>
		 * EPC                 : 0x88<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 何らかの異常の発生状況を示す。<br>
		 * 異常発生有＝0x41、異常発生無＝0x42<br>
		 * <br>
		 * @return ProfileObjectSuperClass.ElInformPropsオブジェクト
		 */
		public ProfileObjectSuperClass.ElInformProps reqInfErrorStatus(){
			listProperty.add(new ElProp(EPC_ERROR_STATUS));
			return this;
		}
		/**
		 * メーカコード<br>
		 * EPC                 : 0x8A<br>
		 * データタイプ        : unsigned char×3<br>
		 * サイズ              : 3 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * ３バイトで指定。<br>
		 * (ECHONET コンソーシアムで規定。)<br>
		 * <br>
		 * @return ProfileObjectSuperClass.ElInformPropsオブジェクト
		 */
		public ProfileObjectSuperClass.ElInformProps reqInfMakerCode(){
			listProperty.add(new ElProp(EPC_MAKER_CODE));
			return this;
		}
		/**
		 * 事業場コード<br>
		 * EPC                 : 0x8B<br>
		 * データタイプ        : unsigned char×3<br>
		 * サイズ              : 3 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 3バイトの事業場コードで指定。<br>
		 * （各メーカ毎に規定。）<br>
		 * <br>
		 * @return ProfileObjectSuperClass.ElInformPropsオブジェクト
		 */
		public ProfileObjectSuperClass.ElInformProps reqInfPlantCode(){
			listProperty.add(new ElProp(EPC_PLANT_CODE));
			return this;
		}
		/**
		 * 商品コード<br>
		 * EPC                 : 0x8C<br>
		 * データタイプ        : unsigned char×12<br>
		 * サイズ              : 12 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * ASCIIコードで指定。<br>
		 * （各メーカ毎に規定。）<br>
		 * <br>
		 * @return ProfileObjectSuperClass.ElInformPropsオブジェクト
		 */
		public ProfileObjectSuperClass.ElInformProps reqInfProductCode(){
			listProperty.add(new ElProp(EPC_PRODUCT_CODE));
			return this;
		}
		/**
		 * 製造番号<br>
		 * EPC                 : 0x8D<br>
		 * データタイプ        : unsigned char ×12<br>
		 * サイズ              : 12 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * ASCIIコードで指定。<br>
		 * （各メーカ毎に規定。）<br>
		 * <br>
		 * @return ProfileObjectSuperClass.ElInformPropsオブジェクト
		 */
		public ProfileObjectSuperClass.ElInformProps reqInfSerialNumber(){
			listProperty.add(new ElProp(EPC_SERIAL_NUMBER));
			return this;
		}
		/**
		 * 製造年月日<br>
		 * EPC                 : 0x8E<br>
		 * データタイプ        : unsigned char×4<br>
		 * サイズ              : 4 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 4バイトで指定。<br>
		 * YYMD (1文字 1ﾊﾞｲﾄ )で示す。<br>
		 * YY ：西暦年 (1999 年の場 合:0x07 CF )<br>
		 * M：月 (12 月の場合＝ 0x0C)<br>
		 * D：日 (20 日の場合＝ 0x14 )<br>
		 * <br>
		 * @return ProfileObjectSuperClass.ElInformPropsオブジェクト
		 */
		public ProfileObjectSuperClass.ElInformProps reqInfMadeYmd(){
			listProperty.add(new ElProp(EPC_MADE_YMD));
			return this;
		}
		/**
		 * 状変アナウンスプロパティマップ<br>
		 * EPC                 : 0x9D<br>
		 * データタイプ        : unsigned char×(MAX17)<br>
		 * サイズ              : Max. 17 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 *「APPENDIX ECHONET機器オブジェクト詳細規定」<br>
		 * 付録 1. 参照<br>
		 * <br>
		 * @return ProfileObjectSuperClass.ElInformPropsオブジェクト
		 */
		public ProfileObjectSuperClass.ElInformProps reqInfConvAnnouncePropMap(){
			listProperty.add(new ElProp(EPC_CONV_ANNOUNCE_PROP_MAP));
			return this;
		}
		/**
		 * Setプロパティマップ<br>
		 * EPC                 : 0x9E<br>
		 * データタイプ        : unsigned char×(MAX17)<br>
		 * サイズ              : Max. 17 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 *「APPENDIX ECHONET機器オブジェクト詳細規定」<br>
		 * 付録 1. 参照<br>
		 * <br>
		 * @return ProfileObjectSuperClass.ElInformPropsオブジェクト
		 */
		public ProfileObjectSuperClass.ElInformProps reqInfSetPropMap(){
			listProperty.add(new ElProp(EPC_SET_PROP_MAP));
			return this;
		}
		/**
		 * Getプロパティマップ<br>
		 * EPC                 : 0x9F<br>
		 * データタイプ        : unsigned char×(MAX17)<br>
		 * サイズ              : Max. 17 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 *「APPENDIX ECHONET機器オブジェクト詳細規定」<br>
		 * 付録 1. 参照<br>
		 * <br>
		 * @return ProfileObjectSuperClass.ElInformPropsオブジェクト
		 */
		public ProfileObjectSuperClass.ElInformProps reqInfGetPropMap(){
			listProperty.add(new ElProp(EPC_GET_PROP_MAP));
			return this;
		}

	}


	/**
	 * 応答、通知系フレームの受信時の処理用クラス
	 */
	public static class ReportProcessor extends ElClassBase.ReportProcessor{

		/**
		 * ログ出力用タグ
		 */
		@SuppressWarnings("unused")
		private static final String TAG = "ProfileObjectSuperClass.ReportProcessor";

		/**
		 * {@inheritDoc}
		 */
		@Override
		protected void onReceivePropertyReportOfSet(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
			super.onReceivePropertyReportOfSet( seoj,  strTid,  strEsv,  objProp,  isSuccess);
		}
		/**
		 * {@inheritDoc}
		 */
		@Override
		protected void onReceivePropertyReportOfGetOrInform(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
			super.onReceivePropertyReportOfGetOrInform( seoj,  strTid,  strEsv,  objProp,  isSuccess);
			switch(objProp.getStrEpc()) {
				case EPC_ERROR_STATUS:
					onGetErrorStatus(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MAKER_CODE:
					onGetMakerCode(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_PLANT_CODE:
					onGetPlantCode(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_PRODUCT_CODE:
					onGetProductCode(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_SERIAL_NUMBER:
					onGetSerialNumber(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MADE_YMD:
					onGetMadeYmd(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_CONV_ANNOUNCE_PROP_MAP:
					onGetConvAnnouncePropMap(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_SET_PROP_MAP:
					onGetSetPropMap(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_GET_PROP_MAP:
					onGetGetPropMap(seoj, strTid, strEsv, objProp, isSuccess);
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
			super.onReceivePropertyReportOfInfC( seoj,  strTid,  strEsv,  objProp);
			switch(objProp.getStrEpc()) {
			case EPC_ERROR_STATUS:
				onInformErrorStatus(seoj, strTid, strEsv, objProp);
				return;
			case EPC_MAKER_CODE:
				onInformMakerCode(seoj, strTid, strEsv, objProp);
				return;
			case EPC_PLANT_CODE:
				onInformPlantCode(seoj, strTid, strEsv, objProp);
				return;
			case EPC_PRODUCT_CODE:
				onInformProductCode(seoj, strTid, strEsv, objProp);
				return;
			case EPC_SERIAL_NUMBER:
				onInformSerialNumber(seoj, strTid, strEsv, objProp);
				return;
			case EPC_MADE_YMD:
				onInformMadeYmd(seoj, strTid, strEsv, objProp);
				return;
			case EPC_CONV_ANNOUNCE_PROP_MAP:
				onInformConvAnnouncePropMap(seoj, strTid, strEsv, objProp);
				return;
			case EPC_SET_PROP_MAP:
				onInformSetPropMap(seoj, strTid, strEsv, objProp);
				return;
			case EPC_GET_PROP_MAP:
				onInformGetPropMap(seoj, strTid, strEsv, objProp);
				return;

			default:
				return;
			}

		}

		/**
		 * Set関連
		 */

		/**
		 * Get,Inf関連
		 */

		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 異常発生状態<br>
		 * EPC                 : 0x88<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 何らかの異常の発生状況を示す。<br>
		 * 異常発生有＝0x41、異常発生無＝0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetErrorStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * メーカコード<br>
		 * EPC                 : 0x8A<br>
		 * データタイプ        : unsigned char×3<br>
		 * サイズ              : 3 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * ３バイトで指定。<br>
		 * (ECHONET コンソーシアムで規定。)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMakerCode(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 事業場コード<br>
		 * EPC                 : 0x8B<br>
		 * データタイプ        : unsigned char×3<br>
		 * サイズ              : 3 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 3バイトの事業場コードで指定。<br>
		 * （各メーカ毎に規定。）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetPlantCode(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 商品コード<br>
		 * EPC                 : 0x8C<br>
		 * データタイプ        : unsigned char×12<br>
		 * サイズ              : 12 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * ASCIIコードで指定。<br>
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
		 * 製造番号<br>
		 * EPC                 : 0x8D<br>
		 * データタイプ        : unsigned char ×12<br>
		 * サイズ              : 12 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * ASCIIコードで指定。<br>
		 * （各メーカ毎に規定。）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetSerialNumber(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 製造年月日<br>
		 * EPC                 : 0x8E<br>
		 * データタイプ        : unsigned char×4<br>
		 * サイズ              : 4 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 4バイトで指定。<br>
		 * YYMD (1文字 1ﾊﾞｲﾄ )で示す。<br>
		 * YY ：西暦年 (1999 年の場 合:0x07 CF )<br>
		 * M：月 (12 月の場合＝ 0x0C)<br>
		 * D：日 (20 日の場合＝ 0x14 )<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMadeYmd(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 状変アナウンスプロパティマップ<br>
		 * EPC                 : 0x9D<br>
		 * データタイプ        : unsigned char×(MAX17)<br>
		 * サイズ              : Max. 17 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 *「APPENDIX ECHONET機器オブジェクト詳細規定」<br>
		 * 付録 1. 参照<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetConvAnnouncePropMap(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
			Set<String> map = seoj.getMapConvAnnounceProps();
			map.clear();
			String[] properties = ElUtil.convHexStrToEachPropValues(objProp.getStrEdt());
			for (String p : properties) {
				map.add(p);
			}
		};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * Setプロパティマップ<br>
		 * EPC                 : 0x9E<br>
		 * データタイプ        : unsigned char×(MAX17)<br>
		 * サイズ              : Max. 17 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 *「APPENDIX ECHONET機器オブジェクト詳細規定」<br>
		 * 付録 1. 参照<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetSetPropMap(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
			Set<String> map = seoj.getMapSetProps();
			map.clear();
			String[] properties = ElUtil.convHexStrToEachPropValues(objProp.getStrEdt());
			for (String p : properties) {
				map.add(p);
			}
		};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * Getプロパティマップ<br>
		 * EPC                 : 0x9F<br>
		 * データタイプ        : unsigned char×(MAX17)<br>
		 * サイズ              : Max. 17 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 *「APPENDIX ECHONET機器オブジェクト詳細規定」<br>
		 * 付録 1. 参照<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetGetPropMap(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
			Set<String> map = seoj.getMapGetProps();
			map.clear();
			String[] properties = ElUtil.convHexStrToEachPropValues(objProp.getStrEdt());
			for (String p : properties) {
				map.add(p);
			}
		};
		/**
		 * InfC関連
		 */
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 異常発生状態<br>
		 * EPC                 : 0x88<br>
		 * データタイプ        : unsigned char<br>
		 * サイズ              : 1 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 何らかの異常の発生状況を示す。<br>
		 * 異常発生有＝0x41、異常発生無＝0x42<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformErrorStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * メーカコード<br>
		 * EPC                 : 0x8A<br>
		 * データタイプ        : unsigned char×3<br>
		 * サイズ              : 3 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * ３バイトで指定。<br>
		 * (ECHONET コンソーシアムで規定。)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMakerCode(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 事業場コード<br>
		 * EPC                 : 0x8B<br>
		 * データタイプ        : unsigned char×3<br>
		 * サイズ              : 3 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 3バイトの事業場コードで指定。<br>
		 * （各メーカ毎に規定。）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformPlantCode(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 商品コード<br>
		 * EPC                 : 0x8C<br>
		 * データタイプ        : unsigned char×12<br>
		 * サイズ              : 12 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * ASCIIコードで指定。<br>
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
		 * 製造番号<br>
		 * EPC                 : 0x8D<br>
		 * データタイプ        : unsigned char ×12<br>
		 * サイズ              : 12 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * ASCIIコードで指定。<br>
		 * （各メーカ毎に規定。）<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformSerialNumber(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 製造年月日<br>
		 * EPC                 : 0x8E<br>
		 * データタイプ        : unsigned char×4<br>
		 * サイズ              : 4 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 4バイトで指定。<br>
		 * YYMD (1文字 1ﾊﾞｲﾄ )で示す。<br>
		 * YY ：西暦年 (1999 年の場 合:0x07 CF )<br>
		 * M：月 (12 月の場合＝ 0x0C)<br>
		 * D：日 (20 日の場合＝ 0x14 )<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMadeYmd(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 状変アナウンスプロパティマップ<br>
		 * EPC                 : 0x9D<br>
		 * データタイプ        : unsigned char×(MAX17)<br>
		 * サイズ              : Max. 17 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 *「APPENDIX ECHONET機器オブジェクト詳細規定」<br>
		 * 付録 1. 参照<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformConvAnnouncePropMap(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * Setプロパティマップ<br>
		 * EPC                 : 0x9E<br>
		 * データタイプ        : unsigned char×(MAX17)<br>
		 * サイズ              : Max. 17 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 *「APPENDIX ECHONET機器オブジェクト詳細規定」<br>
		 * 付録 1. 参照<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformSetPropMap(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * Getプロパティマップ<br>
		 * EPC                 : 0x9F<br>
		 * データタイプ        : unsigned char×(MAX17)<br>
		 * サイズ              : Max. 17 byte<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 *「APPENDIX ECHONET機器オブジェクト詳細規定」<br>
		 * 付録 1. 参照<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformGetPropMap(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};

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
			case EPC_ERROR_STATUS:
				return this.getFromAppErrorStatus();
			case EPC_MAKER_CODE:
				return this.getFromAppMakerCode();
			case EPC_PLANT_CODE:
				return this.getFromAppPlantCode();
			case EPC_PRODUCT_CODE:
				return this.getFromAppProductCode();
			case EPC_SERIAL_NUMBER:
				return this.getFromAppSerialNumber();
			case EPC_MADE_YMD:
				return this.getFromAppMadeYmd();
			case EPC_CONV_ANNOUNCE_PROP_MAP:
				return this.getFromAppConvAnnouncePropMap();
			case EPC_SET_PROP_MAP:
				return this.getFromAppSetPropMap();
			case EPC_GET_PROP_MAP:
				return this.getFromAppGetPropMap();
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
			case EPC_ERROR_STATUS:
				return this.isValidEdtErrorStatus(edt);
			case EPC_MAKER_CODE:
				return this.isValidEdtMakerCode(edt);
			case EPC_PLANT_CODE:
				return this.isValidEdtPlantCode(edt);
			case EPC_PRODUCT_CODE:
				return this.isValidEdtProductCode(edt);
			case EPC_SERIAL_NUMBER:
				return this.isValidEdtSerialNumber(edt);
			case EPC_MADE_YMD:
				return this.isValidEdtMadeYmd(edt);
			case EPC_CONV_ANNOUNCE_PROP_MAP:
				return this.isValidEdtConvAnnouncePropMap(edt);
			case EPC_SET_PROP_MAP:
				return this.isValidEdtSetPropMap(edt);
			case EPC_GET_PROP_MAP:
				return this.isValidEdtGetPropMap(edt);
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
			default:
				return false;
		}
	}

	 // アプリケーションより値を取得するメソッド群
	/**
	 * 異常発生状態<br>
	 * EPC                 : 0x88<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 何らかの異常の発生状況を示す。<br>
	 * 異常発生有＝0x41、異常発生無＝0x42<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppErrorStatus(){return null;}
	/**
	 * メーカコード<br>
	 * EPC                 : 0x8A<br>
	 * データタイプ        : unsigned char×3<br>
	 * サイズ              : 3 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * ３バイトで指定。<br>
	 * (ECHONET コンソーシアムで規定。)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMakerCode(){return null;}
	/**
	 * 事業場コード<br>
	 * EPC                 : 0x8B<br>
	 * データタイプ        : unsigned char×3<br>
	 * サイズ              : 3 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 3バイトの事業場コードで指定。<br>
	 * （各メーカ毎に規定。）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppPlantCode(){return null;}
	/**
	 * 商品コード<br>
	 * EPC                 : 0x8C<br>
	 * データタイプ        : unsigned char×12<br>
	 * サイズ              : 12 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * ASCIIコードで指定。<br>
	 * （各メーカ毎に規定。）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppProductCode(){return null;}
	/**
	 * 製造番号<br>
	 * EPC                 : 0x8D<br>
	 * データタイプ        : unsigned char ×12<br>
	 * サイズ              : 12 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * ASCIIコードで指定。<br>
	 * （各メーカ毎に規定。）<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppSerialNumber(){return null;}
	/**
	 * 製造年月日<br>
	 * EPC                 : 0x8E<br>
	 * データタイプ        : unsigned char×4<br>
	 * サイズ              : 4 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 4バイトで指定。<br>
	 * YYMD (1文字 1ﾊﾞｲﾄ )で示す。<br>
	 * YY ：西暦年 (1999 年の場 合:0x07 CF )<br>
	 * M：月 (12 月の場合＝ 0x0C)<br>
	 * D：日 (20 日の場合＝ 0x14 )<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMadeYmd(){return null;}
	/**
	 * 状変アナウンスプロパティマップ<br>
	 * EPC                 : 0x9D<br>
	 * データタイプ        : unsigned char×(MAX17)<br>
	 * サイズ              : Max. 17 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 *「APPENDIX ECHONET機器オブジェクト詳細規定」<br>
	 * 付録 1. 参照<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppConvAnnouncePropMap(){
		Set<String> propMapSet = this.getMapConvAnnounceProps();
		return ElUtil.convPropMapSetToHexStr(propMapSet);
	}
	/**
	 * Setプロパティマップ<br>
	 * EPC                 : 0x9E<br>
	 * データタイプ        : unsigned char×(MAX17)<br>
	 * サイズ              : Max. 17 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 *「APPENDIX ECHONET機器オブジェクト詳細規定」<br>
	 * 付録 1. 参照<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppSetPropMap(){
		Set<String> propMapSet = this.getMapSetProps();
		return ElUtil.convPropMapSetToHexStr(propMapSet);
	}
	/**
	 * Getプロパティマップ<br>
	 * EPC                 : 0x9F<br>
	 * データタイプ        : unsigned char×(MAX17)<br>
	 * サイズ              : Max. 17 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 *「APPENDIX ECHONET機器オブジェクト詳細規定」<br>
	 * 付録 1. 参照<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppGetPropMap(){
		Set<String> propMapSet = this.getMapGetProps();
		return ElUtil.convPropMapSetToHexStr(propMapSet);
	}

	// アプリケーションから取得した値の妥当性を検証するメソッド群

	/**
	 * 異常発生状態<br>
	 * EPC                 : 0x88<br>
	 * データタイプ        : unsigned char<br>
	 * サイズ              : 1 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 何らかの異常の発生状況を示す。<br>
	 * 異常発生有＝0x41、異常発生無＝0x42<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtErrorStatus(String edt){
		if(edt == null || !(edt.length() == 1 * 2)) return false;
		if(! edt.equalsIgnoreCase(EDT_ERROR_STATUS_TRUE) && ! edt.equalsIgnoreCase(EDT_ERROR_STATUS_FALSE)) return false;
		return true;
	}
	/**
	 * メーカコード<br>
	 * EPC                 : 0x8A<br>
	 * データタイプ        : unsigned char×3<br>
	 * サイズ              : 3 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * ３バイトで指定。<br>
	 * (ECHONET コンソーシアムで規定。)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMakerCode(String edt){
		if(edt == null || !(edt.length() == 3 * 2)) return false;
		return true;
	}
	/**
	 * 事業場コード<br>
	 * EPC                 : 0x8B<br>
	 * データタイプ        : unsigned char×3<br>
	 * サイズ              : 3 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 3バイトの事業場コードで指定。<br>
	 * （各メーカ毎に規定。）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtPlantCode(String edt){
		if(edt == null || !(edt.length() == 3 * 2)) return false;
		return true;
	}
	/**
	 * 商品コード<br>
	 * EPC                 : 0x8C<br>
	 * データタイプ        : unsigned char×12<br>
	 * サイズ              : 12 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * ASCIIコードで指定。<br>
	 * （各メーカ毎に規定。）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtProductCode(String edt){
		if(edt == null || !(edt.length() == 12 * 2)) return false;
		return true;
	}
	/**
	 * 製造番号<br>
	 * EPC                 : 0x8D<br>
	 * データタイプ        : unsigned char ×12<br>
	 * サイズ              : 12 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * ASCIIコードで指定。<br>
	 * （各メーカ毎に規定。）<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtSerialNumber(String edt){
		if(edt == null || !(edt.length() == 12 * 2)) return false;
		return true;
	}
	/**
	 * 製造年月日<br>
	 * EPC                 : 0x8E<br>
	 * データタイプ        : unsigned char×4<br>
	 * サイズ              : 4 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 4バイトで指定。<br>
	 * YYMD (1文字 1ﾊﾞｲﾄ )で示す。<br>
	 * YY ：西暦年 (1999 年の場 合:0x07 CF )<br>
	 * M：月 (12 月の場合＝ 0x0C)<br>
	 * D：日 (20 日の場合＝ 0x14 )<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMadeYmd(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		if(! ElUtil.checkEdtYmdFormat(edt)) return false;
		return true;
	}
	/**
	 * 状変アナウンスプロパティマップ<br>
	 * EPC                 : 0x9D<br>
	 * データタイプ        : unsigned char×(MAX17)<br>
	 * サイズ              : Max. 17 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 *「APPENDIX ECHONET機器オブジェクト詳細規定」<br>
	 * 付録 1. 参照<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtConvAnnouncePropMap(String edt){
		if(edt == null || !(edt.length() <= 17 * 2)) return false;
		return true;
	}
	/**
	 * Setプロパティマップ<br>
	 * EPC                 : 0x9E<br>
	 * データタイプ        : unsigned char×(MAX17)<br>
	 * サイズ              : Max. 17 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 *「APPENDIX ECHONET機器オブジェクト詳細規定」<br>
	 * 付録 1. 参照<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtSetPropMap(String edt){
		if(edt == null || !(edt.length() <= 17 * 2)) return false;
		return true;
	}
	/**
	 * Getプロパティマップ<br>
	 * EPC                 : 0x9F<br>
	 * データタイプ        : unsigned char×(MAX17)<br>
	 * サイズ              : Max. 17 byte<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 *「APPENDIX ECHONET機器オブジェクト詳細規定」<br>
	 * 付録 1. 参照<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtGetPropMap(String edt){
		if(edt == null || !(edt.length() <= 17 * 2)) return false;
		return true;
	}

	// アプリケーションの設置する値の妥当性を検証するメソッド群
	// Set系は無し。

	/**
	 * EPC名称マップ
	 */
	public static Map<String, String> MAP_EPC_NAME = new HashMap<String, String>() {
		{
			put("88", "異常発生状態");
			put("8A", "メーカコード");
			put("8B", "事業場コード");
			put("8C", "商品コード");
			put("8D", "製造番号");
			put("8E", "製造年月日");
			put("9D", "状変アナウンスプロパティマップ");
			put("9E", "Setプロパティマップ");
			put("9F", "Getプロパティマップ");
		}
	};

}
