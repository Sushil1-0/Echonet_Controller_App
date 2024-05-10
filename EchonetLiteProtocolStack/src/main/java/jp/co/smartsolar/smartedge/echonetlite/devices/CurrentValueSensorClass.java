package jp.co.smartsolar.smartedge.echonetlite.devices;

import java.util.HashMap;
import java.util.Map;

import jp.co.smartsolar.smartedge.echonetlite.ElClassBase;
import jp.co.smartsolar.smartedge.echonetlite.ElFrame;
import jp.co.smartsolar.smartedge.echonetlite.ElProcess;
import jp.co.smartsolar.smartedge.echonetlite.ElProp;
import jp.co.smartsolar.smartedge.echonetlite.profile.NodeProfileClass;

/**
 * 電流値センサクラス
 */
public class CurrentValueSensorClass extends DeviceObjectSuperClass{

	/**
	 * ログ出力用タグ
	 */
	@SuppressWarnings("unused")
	private static final String TAG = "CurrentValueSensorClass";


	/**
	 *  クラスグループコード
	 */
	public static final String CLASS_GROUP_CODE = "00";
	/**
	 *  クラスコード
	 */
	public static final String CLASS_CODE = "23";

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
	 * EPC : 電流値計測値１
	 */
	public static final String EPC_MEASURED_CURRENT_VALUE_1 = "E0";

	/**
	 * EPC : 計測対象定格電圧値
	 */
	public static final String EPC_RATED_VOLTAGE_TO_BE_MEASURED = "E1";

	/**
	 * EPC : 電流値計測値２
	 */
	public static final String EPC_MEASURED_CURRENT_VALUE_2 = "E2";



	/**
	 * コンストラクタ
	 */
	public CurrentValueSensorClass() {
		super(CLASS_GROUP_CODE, CLASS_CODE);
	}
	/**
	 * コンストラクタ
	 * @param entityCode エンティティコード（16進数文字列）
	 */
	public CurrentValueSensorClass(String entityCode) {
		super(CLASS_GROUP_CODE, CLASS_CODE, entityCode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initPorpMaps(){
		super.initPorpMaps();

		this.addMapGetProps(EPC_OPERATION_STATUS);
		this.addMapGetProps(EPC_MEASURED_CURRENT_VALUE_1);
//		this.addMapGetProps(EPC_RATED_VOLTAGE_TO_BE_MEASURED); // 必須ではない
		this.addMapGetProps(EPC_MEASURED_CURRENT_VALUE_2);


//		this.addMapSetProps(EPC_OPERATION_STATUS); // 必須ではない


		this.addMapConvAnnounceProps(EPC_OPERATION_STATUS);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onNewEojFound(){
		super.onNewEojFound();
		getElProcess().getElBulkEventProcessor().onNewCurrentValueSensorFound(this);
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
		private static final String TAG = "CurrentValueSensorClass.ElSetProps";

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
		 * @return CurrentValueSensorClass.ElSetPropsオブジェクト
		 */
		public CurrentValueSensorClass.ElSetProps reqSetOperationStatus(String strEdt){
			listProperty.add(new ElProp(EPC_OPERATION_STATUS, strEdt));
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
		private static final String TAG = "CurrentValueSensorClass.ElGetProps";

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
		 * @return CurrentValueSensorClass.ElGetPropsオブジェクト
		 */
		public CurrentValueSensorClass.ElGetProps reqGetOperationStatus(){
			listProperty.add(new ElProp(EPC_OPERATION_STATUS));
			return this;
		}
		/**
		 * 電流値計測値１<br>
		 * EPC                 : 0xE0<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : mA<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須（*1：「電流値計測値１ EPC：0xE0」または「電流値計測値２ EPC：0xE2」のどちらかを必須とする。）<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電流値計測値をmA で示す。<br>
		 * 0x00000000～0xFFFFFFFD<br>
		 * (0～4,294,967,293mA)<br>
		 * <br>
		 * @return CurrentValueSensorClass.ElGetPropsオブジェクト
		 */
		public CurrentValueSensorClass.ElGetProps reqGetMeasuredCurrentValue1(){
			listProperty.add(new ElProp(EPC_MEASURED_CURRENT_VALUE_1));
			return this;
		}
		/**
		 * 計測対象定格電圧値<br>
		 * EPC                 : 0xE1<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : V<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電流量センサ計測対象の定格電圧値<br>
		 * 0x0000～0xFFFD (0～65533V)<br>
		 * <br>
		 * @return CurrentValueSensorClass.ElGetPropsオブジェクト
		 */
		public CurrentValueSensorClass.ElGetProps reqGetRatedVoltageToBeMeasured(){
			listProperty.add(new ElProp(EPC_RATED_VOLTAGE_TO_BE_MEASURED));
			return this;
		}
		/**
		 * 電流値計測値2<br>
		 * EPC                 : 0xE2<br>
		 * データタイプ        : signed long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : mA<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須（*1：「電流値計測値１ EPC：0xE0」または「電流値計測値２ EPC：0xE2」のどちらかを必須とする。）<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電流値計測値をmA で示す。<br>
		 * 0x80000001～0x7FFFFFFE<br>
		 * (－2,147,483,647 ～ 2,147,483,646mA)<br>
		 * <br>
		 * @return CurrentValueSensorClass.ElGetPropsオブジェクト
		 */
		public CurrentValueSensorClass.ElGetProps reqGetMeasuredCurrentValue2(){
			listProperty.add(new ElProp(EPC_MEASURED_CURRENT_VALUE_2));
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
		private static final String TAG = "CurrentValueSensorClass.ElInformProps";

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
		 * @return CurrentValueSensorClass.ElInformPropsオブジェクト
		 */
		public CurrentValueSensorClass.ElInformProps reqInfOperationStatus(){
			listProperty.add(new ElProp(EPC_OPERATION_STATUS));
			return this;
		}
		/**
		 * 電流値計測値１<br>
		 * EPC                 : 0xE0<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : mA<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須（*1：「電流値計測値１ EPC：0xE0」または「電流値計測値２ EPC：0xE2」のどちらかを必須とする。）<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電流値計測値をmA で示す。<br>
		 * 0x00000000～0xFFFFFFFD<br>
		 * (0～4,294,967,293mA)<br>
		 * <br>
		 * @return CurrentValueSensorClass.ElInformPropsオブジェクト
		 */
		public CurrentValueSensorClass.ElInformProps reqInfMeasuredCurrentValue1(){
			listProperty.add(new ElProp(EPC_MEASURED_CURRENT_VALUE_1));
			return this;
		}
		/**
		 * 計測対象定格電圧値<br>
		 * EPC                 : 0xE1<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : V<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電流量センサ計測対象の定格電圧値<br>
		 * 0x0000～0xFFFD (0～65533V)<br>
		 * <br>
		 * @return CurrentValueSensorClass.ElInformPropsオブジェクト
		 */
		public CurrentValueSensorClass.ElInformProps reqInfRatedVoltageToBeMeasured(){
			listProperty.add(new ElProp(EPC_RATED_VOLTAGE_TO_BE_MEASURED));
			return this;
		}
		/**
		 * 電流値計測値2<br>
		 * EPC                 : 0xE2<br>
		 * データタイプ        : signed long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : mA<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須（*1：「電流値計測値１ EPC：0xE0」または「電流値計測値２ EPC：0xE2」のどちらかを必須とする。）<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電流値計測値をmA で示す。<br>
		 * 0x80000001～0x7FFFFFFE<br>
		 * (－2,147,483,647 ～ 2,147,483,646mA)<br>
		 * <br>
		 * @return CurrentValueSensorClass.ElInformPropsオブジェクト
		 */
		public CurrentValueSensorClass.ElInformProps reqInfMeasuredCurrentValue2(){
			listProperty.add(new ElProp(EPC_MEASURED_CURRENT_VALUE_2));
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
	 * @return CurrentValueSensorClass.ElSetPropsオブジェクト
	 */
	public static CurrentValueSensorClass.ElSetProps setIAll(ElProcess elProcess) {
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
	 * @return CurrentValueSensorClass.ElSetPropsオブジェクト
	 */
	public static CurrentValueSensorClass.ElSetProps setIAll(ElProcess elProcess, ElClassBase seoj) {
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
	 * @return CurrentValueSensorClass.ElSetPropsオブジェクト
	 */
	public static CurrentValueSensorClass.ElSetProps setIAll(ElProcess elProcess, String instanceCode) {
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
	 * @return CurrentValueSensorClass.ElSetPropsオブジェクト
	 */
	public static CurrentValueSensorClass.ElSetProps setIAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new ElSetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new CurrentValueSensorClass(instanceCode), ElFrame.ESV_SETI);
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
	 * @return CurrentValueSensorClass.ElSetPropsオブジェクト
	 */
	public static CurrentValueSensorClass.ElSetProps setCAll(ElProcess elProcess) {
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
	 * @return CurrentValueSensorClass.ElSetPropsオブジェクト
	 */
	public static CurrentValueSensorClass.ElSetProps setCAll(ElProcess elProcess, ElClassBase seoj) {
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
	 * @return CurrentValueSensorClass.ElSetPropsオブジェクト
	 */
	public static CurrentValueSensorClass.ElSetProps setCAll(ElProcess elProcess, String instanceCode) {
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
	 * @return CurrentValueSensorClass.ElSetPropsオブジェクト
	 */
	public static CurrentValueSensorClass.ElSetProps setCAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new ElSetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new CurrentValueSensorClass(instanceCode), ElFrame.ESV_SETC);
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
	 * @return CurrentValueSensorClass.ElGetPropsオブジェクト
	 */
	public static CurrentValueSensorClass.ElGetProps getAll(ElProcess elProcess) {
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
	 * @return CurrentValueSensorClass.ElGetPropsオブジェクト
	 */
	public static CurrentValueSensorClass.ElGetProps getAll(ElProcess elProcess, ElClassBase seoj ) {
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
	 * @return CurrentValueSensorClass.ElGetPropsオブジェクト
	 */
	public static CurrentValueSensorClass.ElGetProps getAll(ElProcess elProcess, String instanceCode) {
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
	 * @return CurrentValueSensorClass.ElGetPropsオブジェクト
	 */
	public static CurrentValueSensorClass.ElGetProps getAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new ElGetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new CurrentValueSensorClass(instanceCode), ElFrame.ESV_GET);
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
	 * @return CurrentValueSensorClass.ElInformPropsオブジェクト
	 */
	public static CurrentValueSensorClass.ElInformProps infReqAll(ElProcess elProcess) {
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
	 * @return CurrentValueSensorClass.ElInformPropsオブジェクト
	 */
	public static CurrentValueSensorClass.ElInformProps infReqAll(ElProcess elProcess, ElClassBase seoj ) {
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
	 * @return CurrentValueSensorClass.ElInformPropsオブジェクト
	 */
	public static CurrentValueSensorClass.ElInformProps infReqAll(ElProcess elProcess, String instanceCode) {
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
	 * @return CurrentValueSensorClass.ElInformPropsオブジェクト
	 */
	public static CurrentValueSensorClass.ElInformProps infReqAll(ElProcess elProcess, ElClassBase seoj, String instanceCode) {
		return new ElInformProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new CurrentValueSensorClass(instanceCode), ElFrame.ESV_INF_REQ);
	}

	/**
	 * SetIサービスの処理の指定（個別エンティティ宛て）<br>
	 * リモートIP : DEOJに紐づくIPアドレス<br>
	 * ESV : SETI<br>
	 * DEOJ : this(=リモートノードのEOJ)<br>
	 * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
	 * <br>
	 * 自身thisがリモートノードのEOJ(DEOJ)である前提<br>
	 * <br>
	 * @return CurrentValueSensorClass.ElSetPropsオブジェクト
	 */
	@Override
	public CurrentValueSensorClass.ElSetProps setI(){
		return  setI(getLocalNodeProfile(getElProcess()));
	}

	/**
	 * SetIサービスの処理の指定（個別エンティティ宛て）<br>
	 * リモートIP : DEOJに紐づくIPアドレス<br>
	 * ESV : SETI<br>
	 * DEOJ : this(=リモートノードのEOJ)<br>
	 * SEOJ : ＜パラメータ指定＞<br>
	 * <br>
	 * 自身thisがリモートノードのEOJ(DEOJ)である前提<br>
	 * <br>
	 * @param seoj 送信元EOJ
	 * @return CurrentValueSensorClass.ElSetPropsオブジェクト
	 */
	@Override
	public CurrentValueSensorClass.ElSetProps setI(ElClassBase seoj){
		return new ElSetProps(getElProcess(), this.getNodeBelongsTo().getIpAddress(), seoj, this, ElFrame.ESV_SETI);
	}

	/**
	 * SetCサービスの処理の指定（個別エンティティ宛て）<br>
	 * リモートIP : DEOJに紐づくIPアドレス<br>
	 * ESV : SETC<br>
	 * DEOJ : this(=リモートノードのEOJ)<br>
	 * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
	 * <br>
	 * 自身thisがリモートノードのEOJ(DEOJ)である前提<br>
	 * <br>
	 * @return CurrentValueSensorClass.ElSetPropsオブジェクト
	 */
	@Override
	public CurrentValueSensorClass.ElSetProps setC(){
		return  setC(getLocalNodeProfile(getElProcess()));
	}

	/**
	 * SetCサービスの処理の指定（個別エンティティ宛て）<br>
	 * リモートIP : DEOJに紐づくIPアドレス<br>
	 * ESV : SETC<br>
	 * DEOJ : this(=リモートノードのEOJ)<br>
	 * SEOJ : ＜パラメータ指定＞<br>
	 * <br>
	 * 自身thisがリモートノードのEOJ(DEOJ)である前提<br>
	 * <br>
	 * @param seoj 送信元EOJ
	 * @return CurrentValueSensorClass.ElSetPropsオブジェクト
	 */
	@Override
	public CurrentValueSensorClass.ElSetProps setC(ElClassBase seoj){
		return new ElSetProps(getElProcess(), this.getNodeBelongsTo().getIpAddress(), seoj, this, ElFrame.ESV_SETC);
	}

	/**
	 * Getサービスの処理の指定（個別エンティティ宛て）<br>
	 * リモートIP : DEOJに紐づくIPアドレス<br>
	 * ESV : GET<br>
	 * DEOJ : this(=リモートノードのEOJ)<br>
	 * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
	 * <br>
	 * 自身thisがリモートノードのEOJ(DEOJ)である前提<br>
	 * <br>
	 * @return CurrentValueSensorClass.ElGetPropsオブジェクト
	 */
	@Override
	public CurrentValueSensorClass.ElGetProps get(){
		return get(getLocalNodeProfile(getElProcess()));
	}

	/**
	 * Getサービスの処理の指定（個別エンティティ宛て）<br>
	 * リモートIP : DEOJに紐づくIPアドレス<br>
	 * ESV : GET<br>
	 * DEOJ : this(=リモートノードのEOJ)<br>
	 * SEOJ : ＜パラメータ指定＞<br>
	 * <br>
	 * 自身thisがリモートノードのEOJ(DEOJ)である前提<br>
	 * <br>
	 * @param seoj 送信元EOJ
	 * @return CurrentValueSensorClass.ElGetPropsオブジェクト
	 */
	@Override
	public CurrentValueSensorClass.ElGetProps get(ElClassBase seoj){
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
	 * @return CurrentValueSensorClass.ElInformPropsオブジェクト
	 */
	@Override
	public CurrentValueSensorClass.ElInformProps infReq(){
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
	 * @return CurrentValueSensorClass.ElInformPropsオブジェクト
	 */
	@Override
	public CurrentValueSensorClass.ElInformProps infReq(ElClassBase seoj){
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
	 * @return CurrentValueSensorClass.ElInformPropsオブジェクト
	 */
	@Override
	public CurrentValueSensorClass.ElInformProps inf(){
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
	 * @return CurrentValueSensorClass.ElInformPropsオブジェクト
	 */
	@Override
	public CurrentValueSensorClass.ElInformProps inf(ElClassBase deoj){

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
	 * @return CurrentValueSensorClass.ElInformPropsオブジェクト
	 */
	public CurrentValueSensorClass.ElInformProps infC(String remoteIpAddress){
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
	 * @return CurrentValueSensorClass.ElInformPropsオブジェクト
	 */
	@Override
	public CurrentValueSensorClass.ElInformProps infC(String remoteIpAddress, ElClassBase deoj){
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
		private static final String TAG = "CurrentValueSensorClass.ReportProcessor";

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
				case EPC_MEASURED_CURRENT_VALUE_1:
					onGetMeasuredCurrentValue1(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_RATED_VOLTAGE_TO_BE_MEASURED:
					onGetRatedVoltageToBeMeasured(seoj, strTid, strEsv, objProp, isSuccess);
					return;
				case EPC_MEASURED_CURRENT_VALUE_2:
					onGetMeasuredCurrentValue2(seoj, strTid, strEsv, objProp, isSuccess);
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
				case EPC_MEASURED_CURRENT_VALUE_1:
					onInformMeasuredCurrentValue1(seoj, strTid, strEsv, objProp);
					return;
				case EPC_RATED_VOLTAGE_TO_BE_MEASURED:
					onInformRatedVoltageToBeMeasured(seoj, strTid, strEsv, objProp);
					return;
				case EPC_MEASURED_CURRENT_VALUE_2:
					onInformMeasuredCurrentValue2(seoj, strTid, strEsv, objProp);
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
		 * 電流値計測値１<br>
		 * EPC                 : 0xE0<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : mA<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須（*1：「電流値計測値１ EPC：0xE0」または「電流値計測値２ EPC：0xE2」のどちらかを必須とする。）<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電流値計測値をmA で示す。<br>
		 * 0x00000000～0xFFFFFFFD<br>
		 * (0～4,294,967,293mA)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMeasuredCurrentValue1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 計測対象定格電圧値<br>
		 * EPC                 : 0xE1<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : V<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電流量センサ計測対象の定格電圧値<br>
		 * 0x0000～0xFFFD (0～65533V)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetRatedVoltageToBeMeasured(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
		 * 電流値計測値2<br>
		 * EPC                 : 0xE2<br>
		 * データタイプ        : signed long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : mA<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須（*1：「電流値計測値１ EPC：0xE0」または「電流値計測値２ EPC：0xE2」のどちらかを必須とする。）<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電流値計測値をmA で示す。<br>
		 * 0x80000001～0x7FFFFFFE<br>
		 * (－2,147,483,647 ～ 2,147,483,646mA)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
		 */
		public void onGetMeasuredCurrentValue2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {};

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
		 * 電流値計測値１<br>
		 * EPC                 : 0xE0<br>
		 * データタイプ        : unsigned long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : mA<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須（*1：「電流値計測値１ EPC：0xE0」または「電流値計測値２ EPC：0xE2」のどちらかを必須とする。）<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電流値計測値をmA で示す。<br>
		 * 0x00000000～0xFFFFFFFD<br>
		 * (0～4,294,967,293mA)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMeasuredCurrentValue1(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 計測対象定格電圧値<br>
		 * EPC                 : 0xE1<br>
		 * データタイプ        : unsigned short<br>
		 * サイズ              : 2 byte<br>
		 * 単位                : V<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : オプション<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電流量センサ計測対象の定格電圧値<br>
		 * 0x0000～0xFFFD (0～65533V)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformRatedVoltageToBeMeasured(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
		/**
		 * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
		 * 電流値計測値2<br>
		 * EPC                 : 0xE2<br>
		 * データタイプ        : signed long<br>
		 * サイズ              : 4 byte<br>
		 * 単位                : mA<br>
		 * Anno                : -<br>
		 * Set                 : -<br>
		 * Get                 : 必須（*1：「電流値計測値１ EPC：0xE0」または「電流値計測値２ EPC：0xE2」のどちらかを必須とする。）<br>
		 * <br>
		 * 状変アナウンス      : -<br>
		 * <br>
		 * 備考                : -<br>
		 * <br>
		 * 電流値計測値をmA で示す。<br>
		 * 0x80000001～0x7FFFFFFE<br>
		 * (－2,147,483,647 ～ 2,147,483,646mA)<br>
		 * <br>
		 * @param seoj 受信フレーム内のSEOJ（＝対向ノードのSEOJ）
		 * @param strTid 受信フレームのTID値
		 * @param strEsv 受信フレームのESV値
		 * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
		 */
		public void onInformMeasuredCurrentValue2(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {};
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
			case EPC_MEASURED_CURRENT_VALUE_1:
				return this.getFromAppMeasuredCurrentValue1();
			case EPC_RATED_VOLTAGE_TO_BE_MEASURED:
				return this.getFromAppRatedVoltageToBeMeasured();
			case EPC_MEASURED_CURRENT_VALUE_2:
				return this.getFromAppMeasuredCurrentValue2();
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
			case EPC_MEASURED_CURRENT_VALUE_1:
				return this.isValidEdtMeasuredCurrentValue1(edt);
			case EPC_RATED_VOLTAGE_TO_BE_MEASURED:
				return this.isValidEdtRatedVoltageToBeMeasured(edt);
			case EPC_MEASURED_CURRENT_VALUE_2:
				return this.isValidEdtMeasuredCurrentValue2(edt);
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
	 * 電流値計測値１<br>
	 * EPC                 : 0xE0<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : mA<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須（*1：「電流値計測値１ EPC：0xE0」または「電流値計測値２ EPC：0xE2」のどちらかを必須とする。）<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 電流値計測値をmA で示す。<br>
	 * 0x00000000～0xFFFFFFFD<br>
	 * (0～4,294,967,293mA)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMeasuredCurrentValue1(){return null;}
	/**
	 * 計測対象定格電圧値<br>
	 * EPC                 : 0xE1<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : V<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 電流量センサ計測対象の定格電圧値<br>
	 * 0x0000～0xFFFD (0～65533V)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppRatedVoltageToBeMeasured(){return null;}
	/**
	 * 電流値計測値2<br>
	 * EPC                 : 0xE2<br>
	 * データタイプ        : signed long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : mA<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須（*1：「電流値計測値１ EPC：0xE0」または「電流値計測値２ EPC：0xE2」のどちらかを必須とする。）<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 電流値計測値をmA で示す。<br>
	 * 0x80000001～0x7FFFFFFE<br>
	 * (－2,147,483,647 ～ 2,147,483,646mA)<br>
	 * <br>
	 * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
	 */
	protected String getFromAppMeasuredCurrentValue2(){return null;}

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
	 * 電流値計測値１<br>
	 * EPC                 : 0xE0<br>
	 * データタイプ        : unsigned long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : mA<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須（*1：「電流値計測値１ EPC：0xE0」または「電流値計測値２ EPC：0xE2」のどちらかを必須とする。）<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 電流値計測値をmA で示す。<br>
	 * 0x00000000～0xFFFFFFFD<br>
	 * (0～4,294,967,293mA)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMeasuredCurrentValue1(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
		return true;
	}
	/**
	 * 計測対象定格電圧値<br>
	 * EPC                 : 0xE1<br>
	 * データタイプ        : unsigned short<br>
	 * サイズ              : 2 byte<br>
	 * 単位                : V<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : オプション<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 電流量センサ計測対象の定格電圧値<br>
	 * 0x0000～0xFFFD (0～65533V)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtRatedVoltageToBeMeasured(String edt){
		if(edt == null || !(edt.length() == 2 * 2)) return false;
		return true;
	}
	/**
	 * 電流値計測値2<br>
	 * EPC                 : 0xE2<br>
	 * データタイプ        : signed long<br>
	 * サイズ              : 4 byte<br>
	 * 単位                : mA<br>
	 * Anno                : -<br>
	 * Set                 : -<br>
	 * Get                 : 必須（*1：「電流値計測値１ EPC：0xE0」または「電流値計測値２ EPC：0xE2」のどちらかを必須とする。）<br>
	 * <br>
	 * 状変アナウンス      : -<br>
	 * <br>
	 * 備考                : -<br>
	 * <br>
	 * 電流値計測値をmA で示す。<br>
	 * 0x80000001～0x7FFFFFFE<br>
	 * (－2,147,483,647 ～ 2,147,483,646mA)<br>
	 * <br>
	 * @param edt EDT値（16進数文字列）
	 * @return true: Valid , false Invalid
	 */
	protected boolean isValidEdtMeasuredCurrentValue2(String edt){
		if(edt == null || !(edt.length() == 4 * 2)) return false;
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
	 * EPC名称マップ
	 */
	public static Map<String, String> MAP_EPC_NAME = new HashMap<String, String>() {
		{
			put("80", "動作状態");
			put("E0", "電流値計測値１");
			put("E1", "計測対象定格電圧値");
			put("E2", "電流値計測値2");
		}
	};

}
