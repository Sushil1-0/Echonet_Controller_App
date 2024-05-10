package jp.co.smartsolar.smartedge.echonetlite.profile;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.smartsolar.smartedge.echonetlite.ElClassBase;
import jp.co.smartsolar.smartedge.echonetlite.ElFrame;
import jp.co.smartsolar.smartedge.echonetlite.ElLog;
import jp.co.smartsolar.smartedge.echonetlite.ElProcess;
import jp.co.smartsolar.smartedge.echonetlite.ElProp;
import jp.co.smartsolar.smartedge.echonetlite.ElUtil;
import jp.co.smartsolar.smartedge.echonetlite.devices.DeviceObjectSuperClass;

/**
 * ノードプロファイルクラス
 */
public class NodeProfileClass extends ProfileObjectSuperClass {

    /**
     * ログ出力用タグ
     */
    @SuppressWarnings("unused")
    private static final String TAG = "NodeProfileClass";

    /**
     * クラスグループコード
     */
    public static final String CLASS_GROUP_CODE = "0E";
    /**
     * クラスコード
     */
    public static final String CLASS_CODE = "F0";

    /**
     * インスタンスコード（一般ノード）
     */
    public static final String INSTANCE_CODE_GENERAL = "01";

    /**
     * インスタンスコード（送信専用ノード）
     */
    public static final String INSTANCE_CODE_SEND_ONLY = "02";


    /**
     * EPC : 動作状態
     */
    public static final String EPC_OPERATION_STATUS = "80";
    /**
     * EDT : 動作状態（起動中）
     */
    public static final String EDT_OPERATION_STATUS_ON = "30";
    /**
     * EDT: 動作状態（未起動中）
     */
    public static final String EDT_OPERATION_STATUS_OFF = "31";

    /**
     * EPC : Version情報
     * 通信ミドルウェアが適応しているEchonetLiteのVersion、及びミドルウェアがサポートする電文タイプを示す。
     */
    public static final String EPC_VERSION_INFO = "82";

    /**
     * EPC : 識別番号
     * オブジェクトをドメイン内で一意に識別するための番号。
     */
    public static final String EPC_IDENTIFICATION_NUMBER = "83";

    /**
     * EPC : 異常内容
     * 0x000~0x03EC,0x03FF
     */
    public static final String EPC_ERROR_CONTENT = "89";

    /**
     * EPC : 個体識別情報
     * 2バイトで指定。
     */
    public static final String EPC_INDIVIDUAL_IDENTIFICATION_INFO = "BF";

    /**
     * EPC : 自ノードインスタンス数
     * 自ノードで保持するインスタンスの総数。
     * 1~3バイト
     */
    public static final String EPC_SELF_NODE_INSTANCE_NUM = "D3";

    /**
     * EPC : 自ノードクラス数
     * 自ノードで保持するクラス数。
     * 1~2バイト
     */
    public static final String EPC_SELF_NODE_CLASS_NUM = "D4";


    /**
     * EPC : ノードインスタンスリスト通知
     * 自ノード内インスタンスに構成変化があった時のインスタンスリスト。
     */
    public static final String EPC_INSTANCE_LIST_NOTIFICATION = "D5";

    /**
     * EPC : 自ノードインスタンスリストＳ
     * 自ノード内インスタンスリスト。
     */
    public static final String EPC_SELF_NODE_INSTANCE_LIST_S = "D6";

    /**
     * EPC : 自ノードクラスリストＳ
     * 自ノード内クラスリスト。
     */
    public static final String EPC_SELF_NODE_CLASS_LIST_S = "D7";

    /**
     * 規格Version情報
     */
    public static final String EDT_VERSION_INFORMATION = "010D0100"; // Ver.1.13 規定電文形式


    /**
     * コンストラクタ
     */
    public NodeProfileClass() {
        super(CLASS_GROUP_CODE, CLASS_CODE, INSTANCE_CODE_GENERAL);
    }

    /**
     * コンストラクタ
     *
     * @param entityCode エンティティコード（16進数文字列）
     */
    public NodeProfileClass(String entityCode) {
        super(CLASS_GROUP_CODE, CLASS_CODE, entityCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initPorpMaps() {
        super.initPorpMaps();

        this.addMapGetProps(EPC_OPERATION_STATUS);
        this.addMapGetProps(EPC_VERSION_INFO);
        this.addMapGetProps(EPC_IDENTIFICATION_NUMBER);
//		this.addMapGetProps(EPC_ERROR_CONTENT);  // 必須ではない
        this.addMapGetProps(EPC_INDIVIDUAL_IDENTIFICATION_INFO); // 必須ではない
        this.addMapGetProps(EPC_SELF_NODE_INSTANCE_NUM);
        this.addMapGetProps(EPC_SELF_NODE_CLASS_NUM);
        this.addMapGetProps(EPC_SELF_NODE_INSTANCE_LIST_S);
        this.addMapGetProps(EPC_SELF_NODE_CLASS_LIST_S);

//		this.addMapSetProps(EPC_OPERATION_STATUS); // 必須ではない
        this.addMapSetProps(EPC_INDIVIDUAL_IDENTIFICATION_INFO); // 必須ではない

        this.addMapConvAnnounceProps(EPC_OPERATION_STATUS);
        this.addMapConvAnnounceProps(EPC_INSTANCE_LIST_NOTIFICATION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onNewEojFound() {
        super.onNewEojFound();
        getElProcess().getElBulkEventProcessor().onNewNodeProfileFound(this);
    }


    /**
     * Set系処理の集約クラス
     * 0x60 : プロパティ値書込み要求（応答不要）
     * 0x61 : プロパティ値書込み要求（応答要）
     */
    public static class ElSetProps extends ProfileObjectSuperClass.ElSetProps {

        /**
         * ログ出力用タグ
         */
        @SuppressWarnings("unused")
        private static final String TAG = "NodeProfileClass.ElSetProps";

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
         * Anno                : -<br>
         * Set                 : オプション<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : 必須<br>
         * <br>
         * ノードの動作状態を示す。<br>
         * 起動中 =0x30、未起動中＝0x31<br>
         * <br>
         *
         * @param strEdt EDT値（16進数文字列）
         * @return NodeProfileClass.ElSetPropsオブジェクト
         */
        public NodeProfileClass.ElSetProps reqSetOperationStatus(String strEdt) {
            listProperty.add(new ElProp(EPC_OPERATION_STATUS, strEdt));
            return this;
        }

        /**
         * 個体識別情報<br>
         * EPC                 : 0xBF<br>
         * データタイプ        : unsigned short<br>
         * サイズ              : 2 byte<br>
         * Anno                : -<br>
         * Set                 : オプション<br>
         * Get                 : オプション<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 2バイトで指定。<br>
         * 下記 (3)参照。<br>
         * <br>
         *
         * @param strEdt EDT値（16進数文字列）
         * @return NodeProfileClass.ElSetPropsオブジェクト
         */
        public NodeProfileClass.ElSetProps reqSetIndividualIdentificationInfo(String strEdt) {
            listProperty.add(new ElProp(EPC_INDIVIDUAL_IDENTIFICATION_INFO, strEdt));
            return this;
        }
    }

    /**
     * Get系処理の集約クラス
     * 0x62 : プロパティ値読出し要求
     */
    public static class ElGetProps extends ProfileObjectSuperClass.ElGetProps {

        /**
         * ログ出力用タグ
         */
        @SuppressWarnings("unused")
        private static final String TAG = "NodeProfileClass.ElGetProps";

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
         * Anno                : -<br>
         * Set                 : オプション<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : 必須<br>
         * <br>
         * ノードの動作状態を示す。<br>
         * 起動中 =0x30、未起動中＝0x31<br>
         * <br>
         *
         * @return NodeProfileClass.ElGetPropsオブジェクト
         */
        public NodeProfileClass.ElGetProps reqGetOperationStatus() {
            listProperty.add(new ElProp(EPC_OPERATION_STATUS));
            return this;
        }

        /**
         * Version情報<br>
         * EPC                 : 0x82<br>
         * データタイプ        : unsigned char×4<br>
         * サイズ              : 4 byte<br>
         * Anno                : -<br>
         * Set                 : -<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 通信ミドルウェアが適用している ECHONET Liteの Version、 および通信ミドルウェアがサポートする電文タイプを示す。<br>
         * 1ﾊﾞｲﾄ目：メジャーバョン（小数点以上）をBinaryで示す。<br>
         * 2ﾊﾞｲﾄ目：マイナーバジョン  （小数点 以下 ）をBinaryで示す。<br>
         * 3、4バイト目：電文タイプをビットマップで示す。<br>
         * <br>
         *
         * @return NodeProfileClass.ElGetPropsオブジェクト
         */
        public NodeProfileClass.ElGetProps reqGetVersionInfo() {
            listProperty.add(new ElProp(EPC_VERSION_INFO));
            return this;
        }

        /**
         * 識別番号<br>
         * EPC                 : 0x83<br>
         * データタイプ        : unsigned char×17<br>
         * サイズ              : 17 byte<br>
         * Anno                : -<br>
         * Set                 : -<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * オブジェクトを、ドメイン内で一意に識別するための番号。<br>
         * 1バイト目：下位通信層 ID ﾌｨｰﾙﾄﾞ<br>
         * 0xFE:2～17 バイトをメーカ規定形式により設定<br>
         * その他：future reserved<br>
         * <br>
         *
         * @return NodeProfileClass.ElGetPropsオブジェクト
         */
        public NodeProfileClass.ElGetProps reqGetIdentificationNumber() {
            listProperty.add(new ElProp(EPC_IDENTIFICATION_NUMBER));
            return this;
        }

        /**
         * 異常内容<br>
         * EPC                 : 0x89<br>
         * データタイプ        : unsigned short<br>
         * サイズ              : 2 byte<br>
         * Anno                : -<br>
         * Set                 : -<br>
         * Get                 : オプション<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 異常内容<br>
         * 0x0000 ～0x03EC,0x03FF<br>
         * <br>
         *
         * @return NodeProfileClass.ElGetPropsオブジェクト
         */
        public NodeProfileClass.ElGetProps reqGetErrorContent() {
            listProperty.add(new ElProp(EPC_ERROR_CONTENT));
            return this;
        }

        /**
         * 個体識別情報<br>
         * EPC                 : 0xBF<br>
         * データタイプ        : unsigned short<br>
         * サイズ              : 2 byte<br>
         * Anno                : -<br>
         * Set                 : オプション<br>
         * Get                 : オプション<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 2バイトで指定。<br>
         * 下記 (3)参照。<br>
         * <br>
         *
         * @return NodeProfileClass.ElGetPropsオブジェクト
         */
        public NodeProfileClass.ElGetProps reqGetIndividualIdentificationInfo() {
            listProperty.add(new ElProp(EPC_INDIVIDUAL_IDENTIFICATION_INFO));
            return this;
        }

        /**
         * 自ノードインスタス数<br>
         * EPC                 : 0xD3<br>
         * データタイプ        : unsigned char×3<br>
         * サイズ              : 3 byte<br>
         * Anno                : -<br>
         * Set                 : -<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 自ノードで保持するインスタスの総数。<br>
         * 1～3ﾊﾞｲﾄ：インスタ総数<br>
         * <br>
         *
         * @return NodeProfileClass.ElGetPropsオブジェクト
         */
        public NodeProfileClass.ElGetProps reqGetSelfNodeInstanceNum() {
            listProperty.add(new ElProp(EPC_SELF_NODE_INSTANCE_NUM));
            return this;
        }

        /**
         * 自ノードクラス数<br>
         * EPC                 : 0xD4<br>
         * データタイプ        : unsigned char×2<br>
         * サイズ              : 2 byte<br>
         * Anno                : -<br>
         * Set                 : -<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 自ノードで保持するクラス総数<br>
         * 1～2ﾊﾞｲﾄ：クラス総数<br>
         * <br>
         *
         * @return NodeProfileClass.ElGetPropsオブジェクト
         */
        public NodeProfileClass.ElGetProps reqGetSelfNodeClassNum() {
            listProperty.add(new ElProp(EPC_SELF_NODE_CLASS_NUM));
            return this;
        }

        /**
         * 自ノードインスタスリトS<br>
         * EPC                 : 0xD6<br>
         * データタイプ        : unsigned char× (MAX)253<br>
         * サイズ              :  Max .253 byte<br>
         * Anno                : -<br>
         * Set                 : -<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 自ノード内インスタリト<br>
         * 1ﾊﾞｲﾄ目：インスタンス総数。<br>
         * 2～253ﾊﾞｲﾄ目：ECHONETオ ブジェクトコード（EOJ 3バイ ト）を列挙。<br>
         * <br>
         *
         * @return NodeProfileClass.ElGetPropsオブジェクト
         */
        public NodeProfileClass.ElGetProps reqGetSelfNodeInstanceListS() {
            listProperty.add(new ElProp(EPC_SELF_NODE_INSTANCE_LIST_S));
            return this;
        }

        /**
         * 自ノードクラスリストS<br>
         * EPC                 : 0xD7<br>
         * データタイプ        : unsigned char×(MAX)17<br>
         * サイズ              : 17 byte<br>
         * Anno                : -<br>
         * Set                 : -<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 自ノード内クラスリスト<br>
         * 1ﾊﾞｲﾄ目：クラス総数。<br>
         * 2ﾊﾞｲﾄ目～ 17 ﾊﾞｲﾄ：クラスコード（EOJの上位 2ﾊﾞｲﾄ）を列挙。<br>
         * <br>
         *
         * @return NodeProfileClass.ElGetPropsオブジェクト
         */
        public NodeProfileClass.ElGetProps reqGetSelfNodeClassListS() {
            listProperty.add(new ElProp(EPC_SELF_NODE_CLASS_LIST_S));
            return this;
        }


    }

    /**
     * 通知系処理の集約クラス
     * 0x73 : プロパティ値通知
     * 0x63 : プロパティ値通知要求
     */
    public static class ElInformProps extends ProfileObjectSuperClass.ElInformProps {

        /**
         * ログ出力用タグ
         */
        @SuppressWarnings("unused")
        private static final String TAG = "NodeProfileClass.ElInformProps";

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
         * Anno                : -<br>
         * Set                 : オプション<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : 必須<br>
         * <br>
         * ノードの動作状態を示す。<br>
         * 起動中 =0x30、未起動中＝0x31<br>
         * <br>
         *
         * @return NodeProfileClass.ElInformPropsオブジェクト
         */
        public NodeProfileClass.ElInformProps reqInfOperationStatus() {
            listProperty.add(new ElProp(EPC_OPERATION_STATUS));
            return this;
        }

        /**
         * Version情報<br>
         * EPC                 : 0x82<br>
         * データタイプ        : unsigned char×4<br>
         * サイズ              : 4 byte<br>
         * Anno                : -<br>
         * Set                 : -<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 通信ミドルウェアが適用している ECHONET Liteの Version、 および通信ミドルウェアがサポートする電文タイプを示す。<br>
         * 1ﾊﾞｲﾄ目：メジャーバョン（小数点以上）をBinaryで示す。<br>
         * 2ﾊﾞｲﾄ目：マイナーバジョン  （小数点 以下 ）をBinaryで示す。<br>
         * 3、4バイト目：電文タイプをビットマップで示す。<br>
         * <br>
         *
         * @return NodeProfileClass.ElInformPropsオブジェクト
         */
        public NodeProfileClass.ElInformProps reqInfVersionInfo() {
            listProperty.add(new ElProp(EPC_VERSION_INFO));
            return this;
        }

        /**
         * 識別番号<br>
         * EPC                 : 0x83<br>
         * データタイプ        : unsigned char×17<br>
         * サイズ              : 17 byte<br>
         * Anno                : -<br>
         * Set                 : -<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * オブジェクトを、ドメイン内で一意に識別するための番号。<br>
         * 1バイト目：下位通信層 ID ﾌｨｰﾙﾄﾞ<br>
         * 0xFE:2～17 バイトをメーカ規定形式により設定<br>
         * その他：future reserved<br>
         * <br>
         *
         * @return NodeProfileClass.ElInformPropsオブジェクト
         */
        public NodeProfileClass.ElInformProps reqInfIdentificationNumber() {
            listProperty.add(new ElProp(EPC_IDENTIFICATION_NUMBER));
            return this;
        }

        /**
         * 異常内容<br>
         * EPC                 : 0x89<br>
         * データタイプ        : unsigned short<br>
         * サイズ              : 2 byte<br>
         * Anno                : -<br>
         * Set                 : -<br>
         * Get                 : オプション<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 異常内容<br>
         * 0x0000 ～0x03EC,0x03FF<br>
         * <br>
         *
         * @return NodeProfileClass.ElInformPropsオブジェクト
         */
        public NodeProfileClass.ElInformProps reqInfErrorContent() {
            listProperty.add(new ElProp(EPC_ERROR_CONTENT));
            return this;
        }

        /**
         * 個体識別情報<br>
         * EPC                 : 0xBF<br>
         * データタイプ        : unsigned short<br>
         * サイズ              : 2 byte<br>
         * Anno                : -<br>
         * Set                 : オプション<br>
         * Get                 : オプション<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 2バイトで指定。<br>
         * 下記 (3)参照。<br>
         * <br>
         *
         * @return NodeProfileClass.ElInformPropsオブジェクト
         */
        public NodeProfileClass.ElInformProps reqInfIndividualIdentificationInfo() {
            listProperty.add(new ElProp(EPC_INDIVIDUAL_IDENTIFICATION_INFO));
            return this;
        }

        /**
         * 自ノードインスタス数<br>
         * EPC                 : 0xD3<br>
         * データタイプ        : unsigned char×3<br>
         * サイズ              : 3 byte<br>
         * Anno                : -<br>
         * Set                 : -<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 自ノードで保持するインスタスの総数。<br>
         * 1～3ﾊﾞｲﾄ：インスタ総数<br>
         * <br>
         *
         * @return NodeProfileClass.ElInformPropsオブジェクト
         */
        public NodeProfileClass.ElInformProps reqInfSelfNodeInstanceNum() {
            listProperty.add(new ElProp(EPC_SELF_NODE_INSTANCE_NUM));
            return this;
        }

        /**
         * 自ノードクラス数<br>
         * EPC                 : 0xD4<br>
         * データタイプ        : unsigned char×2<br>
         * サイズ              : 2 byte<br>
         * Anno                : -<br>
         * Set                 : -<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 自ノードで保持するクラス総数<br>
         * 1～2ﾊﾞｲﾄ：クラス総数<br>
         * <br>
         *
         * @return NodeProfileClass.ElInformPropsオブジェクト
         */
        public NodeProfileClass.ElInformProps reqInfSelfNodeClassNum() {
            listProperty.add(new ElProp(EPC_SELF_NODE_CLASS_NUM));
            return this;
        }

        /**
         * インスタンスリスト通知<br>
         * EPC                 : 0xD5<br>
         * データタイプ        : unsigned char×(MAX)253<br>
         * サイズ              : Max . 253 byte<br>
         * Anno                : 必須<br>
         * Set                 : -<br>
         * Get                 : -<br>
         * <br>
         * 状変アナウンス      : 必須<br>
         * <br>
         * 自ノード内インスタンスに構成変化があった時のインスタンスリスト<br>
         * 1ﾊﾞｲﾄ目：通報インスタ数<br>
         * 2ﾊﾞｲﾄ目～253 ﾊﾞｲﾄ：ECHONETオブジェクトコード（ EOJ3バイ ト）を列挙。<br>
         * <br>
         *
         * @return NodeProfileClass.ElInformPropsオブジェクト
         */
        public NodeProfileClass.ElInformProps reqInfInstanceListNotification() {
            listProperty.add(new ElProp(EPC_INSTANCE_LIST_NOTIFICATION));
            return this;
        }

        /**
         * 自ノードインスタスリトS<br>
         * EPC                 : 0xD6<br>
         * データタイプ        : unsigned char× (MAX)253<br>
         * サイズ              :  Max .253 byte<br>
         * Anno                : -<br>
         * Set                 : -<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 自ノード内インスタリト<br>
         * 1ﾊﾞｲﾄ目：インスタンス総数。<br>
         * 2～253ﾊﾞｲﾄ目：ECHONETオ ブジェクトコード（EOJ 3バイ ト）を列挙。<br>
         * <br>
         *
         * @return NodeProfileClass.ElInformPropsオブジェクト
         */
        public NodeProfileClass.ElInformProps reqInfSelfNodeInstanceListS() {
            listProperty.add(new ElProp(EPC_SELF_NODE_INSTANCE_LIST_S));
            return this;
        }

        /**
         * 自ノードクラスリストS<br>
         * EPC                 : 0xD7<br>
         * データタイプ        : unsigned char×(MAX)17<br>
         * サイズ              : 17 byte<br>
         * Anno                : -<br>
         * Set                 : -<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 自ノード内クラスリスト<br>
         * 1ﾊﾞｲﾄ目：クラス総数。<br>
         * 2ﾊﾞｲﾄ目～ 17 ﾊﾞｲﾄ：クラスコード（EOJの上位 2ﾊﾞｲﾄ）を列挙。<br>
         * <br>
         *
         * @return NodeProfileClass.ElInformPropsオブジェクト
         */
        public NodeProfileClass.ElInformProps reqInfSelfNodeClassListS() {
            listProperty.add(new ElProp(EPC_SELF_NODE_CLASS_LIST_S));
            return this;
        }
    }

    /**
     * SetIサービスの処理の指定<br>
     * リモートIP : マルチキャストアドレス<br>
     * ESV : SetI<br>
     * DEOJ : 本クラスのEOJ(インスタンスコードは一般ノードを指定)<br>
     * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
     * <br>
     * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
     * <br>
     *
     * @param elProcess ELプロセスオブジェクト
     * @return NodeProfileClass.ElSetPropsオブジェクト
     */
    public static NodeProfileClass.ElSetProps setIAll(ElProcess elProcess) {
        return setIAll(elProcess, getLocalNodeProfile(elProcess));
    }

    /**
     * SetIサービスの処理の指定<br>
     * リモートIP : マルチキャストアドレス<br>
     * ESV : SetI<br>
     * DEOJ : 本クラスのEOJ(インスタンスコードは一般ノードを指定)<br>
     * SEOJ : ＜パラメータ指定＞<br>
     * <br>
     * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
     * <br>
     *
     * @param elProcess ELプロセスオブジェクト
     * @param seoj      送信元EOJ
     * @return NodeProfileClass.ElSetPropsオブジェクト
     */
    public static NodeProfileClass.ElSetProps setIAll(ElProcess elProcess, ElClassBase seoj) {
        return new ElSetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new NodeProfileClass(INSTANCE_CODE_GENERAL), ElFrame.ESV_SETI);
    }


    /**
     * SetCサービスの処理の指定<br>
     * リモートIP : マルチキャストアドレス<br>
     * ESV : SetC<br>
     * DEOJ : 本クラスのEOJ(インスタンスコードは一般ノードを指定)<br>
     * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
     * <br>
     * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
     * <br>
     *
     * @param elProcess ELプロセスオブジェクト
     * @return NodeProfileClass.ElSetPropsオブジェクト
     */
    public static NodeProfileClass.ElSetProps setCAll(ElProcess elProcess) {
        return setCAll(elProcess, getLocalNodeProfile(elProcess));
    }

    /**
     * SetCサービスの処理の指定<br>
     * リモートIP : マルチキャストアドレス<br>
     * ESV : SetC<br>
     * DEOJ : 本クラスのEOJ(インスタンスコードは一般ノードを指定)<br>
     * SEOJ : ＜パラメータ指定＞<br>
     * <br>
     * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
     * <br>
     *
     * @param elProcess ELプロセスオブジェクト
     * @param seoj      送信元EOJ
     * @return NodeProfileClass.ElSetPropsオブジェクト
     */
    public static NodeProfileClass.ElSetProps setCAll(ElProcess elProcess, ElClassBase seoj) {
        return new ElSetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new NodeProfileClass(INSTANCE_CODE_GENERAL), ElFrame.ESV_SETI);
    }

    /**
     * Getサービスの処理の指定<br>
     * リモートIP : マルチキャストアドレス<br>
     * ESV : Get<br>
     * DEOJ : 本クラスのEOJ(インスタンスコードは一般ノードを指定)<br>
     * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
     * <br>
     * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
     * <br>
     *
     * @param elProcess ELプロセスオブジェクト
     * @return NodeProfileClass.ElGetPropsオブジェクト
     */
    public static NodeProfileClass.ElGetProps getAll(ElProcess elProcess) {
        return getAll(elProcess, getLocalNodeProfile(elProcess));
    }

    /**
     * Getサービスの処理の指定<br>
     * リモートIP : マルチキャストアドレス<br>
     * ESV : Get<br>
     * DEOJ : 本クラスのEOJ(インスタンスコードは一般ノードを指定)<br>
     * SEOJ : ＜パラメータ指定＞<br>
     * <br>
     * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
     * <br>
     *
     * @param elProcess ELプロセスオブジェクト
     * @param seoj      送信元EOJ
     * @return NodeProfileClass.ElGetPropsオブジェクト
     */
    public static NodeProfileClass.ElGetProps getAll(ElProcess elProcess, ElClassBase seoj) {
        return new ElGetProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new NodeProfileClass(INSTANCE_CODE_GENERAL), ElFrame.ESV_GET);
    }

    /**
     * INF_REQサービスの処理の指定<br>
     * リモートIP : マルチキャストアドレス<br>
     * ESV : INF_REQ<br>
     * DEOJ : 本クラスのEOJ(インスタンスコードは一般ノードを指定)<br>
     * SEOJ : ローカルノードのNodeProfileオブジェクト<br>
     * <br>
     * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
     * <br>
     *
     * @param elProcess ELプロセスオブジェクト
     * @return NodeProfileClass.ElInformPropsオブジェクト
     */
    public static NodeProfileClass.ElInformProps infReqAll(ElProcess elProcess) {
        return infReqAll(elProcess, getLocalNodeProfile(elProcess));
    }


    /**
     * INF_REQサービスの処理の指定<br>
     * リモートIP : マルチキャストアドレス<br>
     * ESV : INF_REQ<br>
     * DEOJ : 本クラスのEOJ(インスタンスコードは一般ノードを指定)<br>
     * SEOJ : ＜パラメータ指定＞<br>
     * <br>
     * 本メソッドの所属するクラスが宛先のEOJクラスとなる<br>
     * <br>
     *
     * @param elProcess ELプロセスオブジェクト
     * @param seoj      送信元EOJ
     * @return NodeProfileClass.ElInformPropsオブジェクト
     */
    public static NodeProfileClass.ElInformProps infReqAll(ElProcess elProcess, ElClassBase seoj) {
        return new ElInformProps(elProcess, elProcess.getChannel().getMultiCastAddress(), seoj, new NodeProfileClass(INSTANCE_CODE_GENERAL), ElFrame.ESV_INF_REQ);
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
     *
     * @return NodeProfileClass.ElSetPropsオブジェクト
     */
    public NodeProfileClass.ElSetProps setI() {
        return setI(getLocalNodeProfile(getElProcess()));
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
     *
     * @param seoj 送信元EOJ
     * @return NodeProfileClass.ElSetPropsオブジェクト
     */
    public NodeProfileClass.ElSetProps setI(ElClassBase seoj) {
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
     *
     * @return NodeProfileClass.ElSetPropsオブジェクト
     */
    public NodeProfileClass.ElSetProps setC() {
        return setC(getLocalNodeProfile(getElProcess()));
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
     *
     * @param seoj 送信元EOJ
     * @return NodeProfileClass.ElSetPropsオブジェクト
     */
    public NodeProfileClass.ElSetProps setC(ElClassBase seoj) {
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
     *
     * @return NodeProfileClass.ElGetPropsオブジェクト
     */
    public NodeProfileClass.ElGetProps get() {
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
     *
     * @param seoj 送信元EOJ
     * @return NodeProfileClass.ElGetPropsオブジェクト
     */
    public NodeProfileClass.ElGetProps get(ElClassBase seoj) {
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
     *
     * @return NodeProfileClass.ElInformPropsオブジェクト
     */
    public NodeProfileClass.ElInformProps infReq() {
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
     *
     * @param seoj 送信元EOJ
     * @return NodeProfileClass.ElInformPropsオブジェクト
     */
    public NodeProfileClass.ElInformProps infReq(ElClassBase seoj) {
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
     *
     * @return NodeProfileClass.ElInformPropsオブジェクト
     */
    public NodeProfileClass.ElInformProps inf() {
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
     *
     * @param deoj 宛先EOJ
     * @return NodeProfileClass.ElInformPropsオブジェクト
     */
    public NodeProfileClass.ElInformProps inf(ElClassBase deoj) {

        boolean isWithMuticalst = false;
        // deojで所属するノードのIPアドレスが取得できない場合は、無条件にマルチキャストとする。
        if (deoj.getNodeBelongsTo() == null
                || deoj.getNodeBelongsTo().getIpAddress() == null
                || deoj.getNodeBelongsTo().getIpAddress().isEmpty()) {
            isWithMuticalst = true;
        }

        // 宛先IP
        String sIp = getElProcess().getChannel().getMultiCastAddress();
        ElClassBase objDeoj = deoj;
        if (isWithMuticalst) {
            // DEOJのエンティティコード部分を00に変更する。
            // 下記により、管理下のDEOJオブジェクトを変更せずに新規でEOJを生成する
            // objDeoj = ElClassBase.instanceEojByCode(deoj.getStrClassGroupCode(), deoj.getStrClassCode(), ElClassBase.INSTANCE_CODE_ALL);
        } else {
            sIp = deoj.getNodeBelongsTo().getIpAddress();
        }
        return new ElInformProps(getElProcess(), sIp, this, objDeoj, ElFrame.ESV_INF);
    }

    public NodeProfileClass.ElInformProps inf(String remoteIp) {
        return inf(new NodeProfileClass(), remoteIp);// INSTANCE_CODE_GENERAL
    }

    public NodeProfileClass.ElInformProps inf(ElClassBase deoj, String remoteIp) {

        boolean isWithMuticalst = false;
        // deojで所属するノードのIPアドレスが取得できない場合は、無条件にマルチキャストとする。
        if (deoj.getNodeBelongsTo() == null
                || deoj.getNodeBelongsTo().getIpAddress() == null
                || deoj.getNodeBelongsTo().getIpAddress().isEmpty()) {
            isWithMuticalst = true;
        }


        return new ElInformProps(getElProcess(), remoteIp, this, deoj, ElFrame.ESV_INF);
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
     *
     * @param remoteIpAddress 宛先のIPアドレス
     * @return NodeProfileClass.ElInformPropsオブジェクト
     */
    public NodeProfileClass.ElInformProps infC(String remoteIpAddress) {
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
     *
     * @param remoteIpAddress 宛先のIPアドレス
     * @param deoj            宛先EOJ
     * @return NodeProfileClass.ElInformPropsオブジェクト
     */
    public NodeProfileClass.ElInformProps infC(String remoteIpAddress, ElClassBase deoj) {
        return new ElInformProps(getElProcess(), remoteIpAddress, this, deoj, ElFrame.ESV_INFC);
    }


    /**
     * 応答、通知系フレームの受信時の処理用クラス
     */
    public static class ReportProcessor extends ElClassBase.ReportProcessor {

        /**
         * ログ出力用タグ
         */
        @SuppressWarnings("unused")
        private static final String TAG = "NodeProfileClass.ReportProcessor";

        /**
         * {@inheritDoc}
         */
        @Override
        protected void onReceivePropertyReportOfSet(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
            super.onReceivePropertyReportOfSet(seoj, strTid, strEsv, objProp, isSuccess);
            switch (objProp.getStrEpc()) {
                case EPC_OPERATION_STATUS:
                    onSetOperationStatus(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_INDIVIDUAL_IDENTIFICATION_INFO:
                    onSetIndividualIdentificationInfo(seoj, strTid, strEsv, objProp, isSuccess);
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
            super.onReceivePropertyReportOfGetOrInform(seoj, strTid, strEsv, objProp, isSuccess);
            switch (objProp.getStrEpc()) {
                case EPC_OPERATION_STATUS:
                    onGetOperationStatus(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_VERSION_INFO:
                    onGetVersionInfo(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_IDENTIFICATION_NUMBER:
                    onGetIdentificationNumber(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_ERROR_CONTENT:
                    onGetErrorContent(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_INDIVIDUAL_IDENTIFICATION_INFO:
                    onGetIndividualIdentificationInfo(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_SELF_NODE_INSTANCE_NUM:
                    onGetSelfNodeInstanceNum(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_SELF_NODE_CLASS_NUM:
                    onGetSelfNodeClassNum(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_INSTANCE_LIST_NOTIFICATION:
                    onGetInstanceListNotification(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_SELF_NODE_INSTANCE_LIST_S:
                    onGetSelfNodeInstanceListS(seoj, strTid, strEsv, objProp, isSuccess);
                    return;
                case EPC_SELF_NODE_CLASS_LIST_S:
                    onGetSelfNodeClassListS(seoj, strTid, strEsv, objProp, isSuccess);
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
            super.onReceivePropertyReportOfInfC(seoj, strTid, strEsv, objProp);
            switch (objProp.getStrEpc()) {
                case EPC_OPERATION_STATUS:
                    onInformOperationStatus(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_VERSION_INFO:
                    onInformVersionInfo(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_IDENTIFICATION_NUMBER:
                    onInformIdentificationNumber(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_ERROR_CONTENT:
                    onInformErrorContent(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_INDIVIDUAL_IDENTIFICATION_INFO:
                    onInformIndividualIdentificationInfo(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_SELF_NODE_INSTANCE_NUM:
                    onInformSelfNodeInstanceNum(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_SELF_NODE_CLASS_NUM:
                    onInformSelfNodeClassNum(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_INSTANCE_LIST_NOTIFICATION:
                    onInformInstanceListNotification(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_SELF_NODE_INSTANCE_LIST_S:
                    onInformSelfNodeInstanceListS(seoj, strTid, strEsv, objProp);
                    return;
                case EPC_SELF_NODE_CLASS_LIST_S:
                    onInformSelfNodeClassListS(seoj, strTid, strEsv, objProp);
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
         * Anno                : -<br>
         * Set                 : オプション<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : 必須<br>
         * <br>
         * ノードの動作状態を示す。<br>
         * 起動中 =0x30、未起動中＝0x31<br>
         * <br>
         *
         * @param seoj      受信フレーム内のSEOJ（＝対向ノードのSEOJ）
         * @param strTid    受信フレームのTID値
         * @param strEsv    受信フレームのESV値
         * @param objProp   受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
         * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
         */
        public void onSetOperationStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        }

        ;

        /**
         * 応答受信時のプロパティ別コールバック処理（Set系）<br>
         * 個体識別情報<br>
         * EPC                 : 0xBF<br>
         * データタイプ        : unsigned short<br>
         * サイズ              : 2 byte<br>
         * Anno                : -<br>
         * Set                 : オプション<br>
         * Get                 : オプション<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 2バイトで指定。<br>
         * 下記 (3)参照。<br>
         * <br>
         *
         * @param seoj      受信フレーム内のSEOJ（＝対向ノードのSEOJ）
         * @param strTid    受信フレームのTID値
         * @param strEsv    受信フレームのESV値
         * @param objProp   受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
         * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
         */
        public void onSetIndividualIdentificationInfo(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        }

        ;

        /**
         * Get,Inf関連
         */

        /**
         * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
         * 動作状態<br>
         * EPC                 : 0x80<br>
         * データタイプ        : unsigned char<br>
         * サイズ              : 1 byte<br>
         * Anno                : -<br>
         * Set                 : オプション<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : 必須<br>
         * <br>
         * ノードの動作状態を示す。<br>
         * 起動中 =0x30、未起動中＝0x31<br>
         * <br>
         *
         * @param seoj      受信フレーム内のSEOJ（＝対向ノードのSEOJ）
         * @param strTid    受信フレームのTID値
         * @param strEsv    受信フレームのESV値
         * @param objProp   受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
         * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
         */
        public void onGetOperationStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        }

        ;

        /**
         * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
         * Version情報<br>
         * EPC                 : 0x82<br>
         * データタイプ        : unsigned char×4<br>
         * サイズ              : 4 byte<br>
         * Anno                : -<br>
         * Set                 : -<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 通信ミドルウェアが適用している ECHONET Liteの Version、 および通信ミドルウェアがサポートする電文タイプを示す。<br>
         * 1ﾊﾞｲﾄ目：メジャーバョン（小数点以上）をBinaryで示す。<br>
         * 2ﾊﾞｲﾄ目：マイナーバジョン  （小数点 以下 ）をBinaryで示す。<br>
         * 3、4バイト目：電文タイプをビットマップで示す。<br>
         * <br>
         *
         * @param seoj      受信フレーム内のSEOJ（＝対向ノードのSEOJ）
         * @param strTid    受信フレームのTID値
         * @param strEsv    受信フレームのESV値
         * @param objProp   受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
         * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
         */
        public void onGetVersionInfo(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        }

        ;

        /**
         * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
         * 識別番号<br>
         * EPC                 : 0x83<br>
         * データタイプ        : unsigned char×17<br>
         * サイズ              : 17 byte<br>
         * Anno                : -<br>
         * Set                 : -<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * オブジェクトを、ドメイン内で一意に識別するための番号。<br>
         * 1バイト目：下位通信層 ID ﾌｨｰﾙﾄﾞ<br>
         * 0xFE:2～17 バイトをメーカ規定形式により設定<br>
         * その他：future reserved<br>
         * <br>
         *
         * @param seoj      受信フレーム内のSEOJ（＝対向ノードのSEOJ）
         * @param strTid    受信フレームのTID値
         * @param strEsv    受信フレームのESV値
         * @param objProp   受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
         * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
         */
        public void onGetIdentificationNumber(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        }

        ;

        /**
         * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
         * 異常内容<br>
         * EPC                 : 0x89<br>
         * データタイプ        : unsigned short<br>
         * サイズ              : 2 byte<br>
         * Anno                : -<br>
         * Set                 : -<br>
         * Get                 : オプション<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 異常内容<br>
         * 0x0000 ～0x03EC,0x03FF<br>
         * <br>
         *
         * @param seoj      受信フレーム内のSEOJ（＝対向ノードのSEOJ）
         * @param strTid    受信フレームのTID値
         * @param strEsv    受信フレームのESV値
         * @param objProp   受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
         * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
         */
        public void onGetErrorContent(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        }

        ;

        /**
         * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
         * 個体識別情報<br>
         * EPC                 : 0xBF<br>
         * データタイプ        : unsigned short<br>
         * サイズ              : 2 byte<br>
         * Anno                : -<br>
         * Set                 : オプション<br>
         * Get                 : オプション<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 2バイトで指定。<br>
         * 下記 (3)参照。<br>
         * <br>
         *
         * @param seoj      受信フレーム内のSEOJ（＝対向ノードのSEOJ）
         * @param strTid    受信フレームのTID値
         * @param strEsv    受信フレームのESV値
         * @param objProp   受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
         * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
         */
        public void onGetIndividualIdentificationInfo(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        }

        ;

        /**
         * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
         * 自ノードインスタス数<br>
         * EPC                 : 0xD3<br>
         * データタイプ        : unsigned char×3<br>
         * サイズ              : 3 byte<br>
         * Anno                : -<br>
         * Set                 : -<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 自ノードで保持するインスタスの総数。<br>
         * 1～3ﾊﾞｲﾄ：インスタ総数<br>
         * <br>
         *
         * @param seoj      受信フレーム内のSEOJ（＝対向ノードのSEOJ）
         * @param strTid    受信フレームのTID値
         * @param strEsv    受信フレームのESV値
         * @param objProp   受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
         * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
         */
        public void onGetSelfNodeInstanceNum(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        }

        ;

        /**
         * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
         * 自ノードクラス数<br>
         * EPC                 : 0xD4<br>
         * データタイプ        : unsigned char×2<br>
         * サイズ              : 2 byte<br>
         * Anno                : -<br>
         * Set                 : -<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 自ノードで保持するクラス総数<br>
         * 1～2ﾊﾞｲﾄ：クラス総数<br>
         * <br>
         *
         * @param seoj      受信フレーム内のSEOJ（＝対向ノードのSEOJ）
         * @param strTid    受信フレームのTID値
         * @param strEsv    受信フレームのESV値
         * @param objProp   受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
         * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
         */
        public void onGetSelfNodeClassNum(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        }

        ;

        /**
         * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
         * インスタンスリスト通知<br>
         * EPC                 : 0xD5<br>
         * データタイプ        : unsigned char×(MAX)253<br>
         * サイズ              : Max . 253 byte<br>
         * Anno                : 必須<br>
         * Set                 : -<br>
         * Get                 : -<br>
         * <br>
         * 状変アナウンス      : 必須<br>
         * <br>
         * 自ノード内インスタンスに構成変化があった時のインスタンスリスト<br>
         * 1ﾊﾞｲﾄ目：通報インスタ数<br>
         * 2ﾊﾞｲﾄ目～253 ﾊﾞｲﾄ：ECHONETオブジェクトコード（ EOJ3バイ ト）を列挙。<br>
         * <br>
         *
         * @param seoj      受信フレーム内のSEOJ（＝対向ノードのSEOJ）
         * @param strTid    受信フレームのTID値
         * @param strEsv    受信フレームのESV値
         * @param objProp   受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
         * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
         */
        public void onGetInstanceListNotification(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        }

        ;

        /**
         * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
         * 自ノードインスタスリトS<br>
         * EPC                 : 0xD6<br>
         * データタイプ        : unsigned char× (MAX)253<br>
         * サイズ              :  Max .253 byte<br>
         * Anno                : -<br>
         * Set                 : -<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 自ノード内インスタリト<br>
         * 1ﾊﾞｲﾄ目：インスタンス総数。<br>
         * 2～253ﾊﾞｲﾄ目：ECHONETオ ブジェクトコード（EOJ 3バイ ト）を列挙。<br>
         * <br>
         *
         * @param seoj      受信フレーム内のSEOJ（＝対向ノードのSEOJ）
         * @param strTid    受信フレームのTID値
         * @param strEsv    受信フレームのESV値
         * @param objProp   受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
         * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
         */
        public void onGetSelfNodeInstanceListS(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        }

        ;

        /**
         * 応答受信時のプロパティ別コールバック処理（Get系、Inf系）<br>
         * 自ノードクラスリストS<br>
         * EPC                 : 0xD7<br>
         * データタイプ        : unsigned char×(MAX)17<br>
         * サイズ              : 17 byte<br>
         * Anno                : -<br>
         * Set                 : -<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 自ノード内クラスリスト<br>
         * 1ﾊﾞｲﾄ目：クラス総数。<br>
         * 2ﾊﾞｲﾄ目～ 17 ﾊﾞｲﾄ：クラスコード（EOJの上位 2ﾊﾞｲﾄ）を列挙。<br>
         * <br>
         *
         * @param seoj      受信フレーム内のSEOJ（＝対向ノードのSEOJ）
         * @param strTid    受信フレームのTID値
         * @param strEsv    受信フレームのESV値
         * @param objProp   受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
         * @param isSuccess 要求受理の成否（true : 受理、false : 不可）
         */
        public void onGetSelfNodeClassListS(ElClassBase seoj, String strTid, String strEsv, ElProp objProp, boolean isSuccess) {
        }

        ;

        /**
         * InfC関連
         */
        /**
         * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
         * 動作状態<br>
         * EPC                 : 0x80<br>
         * データタイプ        : unsigned char<br>
         * サイズ              : 1 byte<br>
         * Anno                : -<br>
         * Set                 : オプション<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : 必須<br>
         * <br>
         * ノードの動作状態を示す。<br>
         * 起動中 =0x30、未起動中＝0x31<br>
         * <br>
         *
         * @param seoj    受信フレーム内のSEOJ（＝対向ノードのSEOJ）
         * @param strTid  受信フレームのTID値
         * @param strEsv  受信フレームのESV値
         * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
         */
        public void onInformOperationStatus(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {
        }

        ;

        /**
         * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
         * Version情報<br>
         * EPC                 : 0x82<br>
         * データタイプ        : unsigned char×4<br>
         * サイズ              : 4 byte<br>
         * Anno                : -<br>
         * Set                 : -<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 通信ミドルウェアが適用している ECHONET Liteの Version、 および通信ミドルウェアがサポートする電文タイプを示す。<br>
         * 1ﾊﾞｲﾄ目：メジャーバョン（小数点以上）をBinaryで示す。<br>
         * 2ﾊﾞｲﾄ目：マイナーバジョン  （小数点 以下 ）をBinaryで示す。<br>
         * 3、4バイト目：電文タイプをビットマップで示す。<br>
         * <br>
         *
         * @param seoj    受信フレーム内のSEOJ（＝対向ノードのSEOJ）
         * @param strTid  受信フレームのTID値
         * @param strEsv  受信フレームのESV値
         * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
         */
        public void onInformVersionInfo(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {
        }

        ;

        /**
         * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
         * 識別番号<br>
         * EPC                 : 0x83<br>
         * データタイプ        : unsigned char×17<br>
         * サイズ              : 17 byte<br>
         * Anno                : -<br>
         * Set                 : -<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * オブジェクトを、ドメイン内で一意に識別するための番号。<br>
         * 1バイト目：下位通信層 ID ﾌｨｰﾙﾄﾞ<br>
         * 0xFE:2～17 バイトをメーカ規定形式により設定<br>
         * その他：future reserved<br>
         * <br>
         *
         * @param seoj    受信フレーム内のSEOJ（＝対向ノードのSEOJ）
         * @param strTid  受信フレームのTID値
         * @param strEsv  受信フレームのESV値
         * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
         */
        public void onInformIdentificationNumber(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {
        }

        ;

        /**
         * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
         * 異常内容<br>
         * EPC                 : 0x89<br>
         * データタイプ        : unsigned short<br>
         * サイズ              : 2 byte<br>
         * Anno                : -<br>
         * Set                 : -<br>
         * Get                 : オプション<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 異常内容<br>
         * 0x0000 ～0x03EC,0x03FF<br>
         * <br>
         *
         * @param seoj    受信フレーム内のSEOJ（＝対向ノードのSEOJ）
         * @param strTid  受信フレームのTID値
         * @param strEsv  受信フレームのESV値
         * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
         */
        public void onInformErrorContent(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {
        }

        ;

        /**
         * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
         * 個体識別情報<br>
         * EPC                 : 0xBF<br>
         * データタイプ        : unsigned short<br>
         * サイズ              : 2 byte<br>
         * Anno                : -<br>
         * Set                 : オプション<br>
         * Get                 : オプション<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 2バイトで指定。<br>
         * 下記 (3)参照。<br>
         * <br>
         *
         * @param seoj    受信フレーム内のSEOJ（＝対向ノードのSEOJ）
         * @param strTid  受信フレームのTID値
         * @param strEsv  受信フレームのESV値
         * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
         */
        public void onInformIndividualIdentificationInfo(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {
        }

        ;

        /**
         * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
         * 自ノードインスタス数<br>
         * EPC                 : 0xD3<br>
         * データタイプ        : unsigned char×3<br>
         * サイズ              : 3 byte<br>
         * Anno                : -<br>
         * Set                 : -<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 自ノードで保持するインスタスの総数。<br>
         * 1～3ﾊﾞｲﾄ：インスタ総数<br>
         * <br>
         *
         * @param seoj    受信フレーム内のSEOJ（＝対向ノードのSEOJ）
         * @param strTid  受信フレームのTID値
         * @param strEsv  受信フレームのESV値
         * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
         */
        public void onInformSelfNodeInstanceNum(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {
        }

        ;

        /**
         * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
         * 自ノードクラス数<br>
         * EPC                 : 0xD4<br>
         * データタイプ        : unsigned char×2<br>
         * サイズ              : 2 byte<br>
         * Anno                : -<br>
         * Set                 : -<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 自ノードで保持するクラス総数<br>
         * 1～2ﾊﾞｲﾄ：クラス総数<br>
         * <br>
         *
         * @param seoj    受信フレーム内のSEOJ（＝対向ノードのSEOJ）
         * @param strTid  受信フレームのTID値
         * @param strEsv  受信フレームのESV値
         * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
         */
        public void onInformSelfNodeClassNum(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {
        }

        ;

        /**
         * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
         * インスタンスリスト通知<br>
         * EPC                 : 0xD5<br>
         * データタイプ        : unsigned char×(MAX)253<br>
         * サイズ              : Max . 253 byte<br>
         * Anno                : 必須<br>
         * Set                 : -<br>
         * Get                 : -<br>
         * <br>
         * 状変アナウンス      : 必須<br>
         * <br>
         * 自ノード内インスタンスに構成変化があった時のインスタンスリスト<br>
         * 1ﾊﾞｲﾄ目：通報インスタ数<br>
         * 2ﾊﾞｲﾄ目～253 ﾊﾞｲﾄ：ECHONETオブジェクトコード（ EOJ3バイ ト）を列挙。<br>
         * <br>
         *
         * @param seoj    受信フレーム内のSEOJ（＝対向ノードのSEOJ）
         * @param strTid  受信フレームのTID値
         * @param strEsv  受信フレームのESV値
         * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
         */
        public void onInformInstanceListNotification(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {
        }

        ;

        /**
         * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
         * 自ノードインスタスリトS<br>
         * EPC                 : 0xD6<br>
         * データタイプ        : unsigned char× (MAX)253<br>
         * サイズ              :  Max .253 byte<br>
         * Anno                : -<br>
         * Set                 : -<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 自ノード内インスタリト<br>
         * 1ﾊﾞｲﾄ目：インスタンス総数。<br>
         * 2～253ﾊﾞｲﾄ目：ECHONETオ ブジェクトコード（EOJ 3バイ ト）を列挙。<br>
         * <br>
         *
         * @param seoj    受信フレーム内のSEOJ（＝対向ノードのSEOJ）
         * @param strTid  受信フレームのTID値
         * @param strEsv  受信フレームのESV値
         * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
         */
        public void onInformSelfNodeInstanceListS(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {
        }

        ;

        /**
         * 応答受信時のプロパティ別コールバック処理（InfC系）<br>
         * 自ノードクラスリストS<br>
         * EPC                 : 0xD7<br>
         * データタイプ        : unsigned char×(MAX)17<br>
         * サイズ              : 17 byte<br>
         * Anno                : -<br>
         * Set                 : -<br>
         * Get                 : 必須<br>
         * <br>
         * 状変アナウンス      : -<br>
         * <br>
         * 自ノード内クラスリスト<br>
         * 1ﾊﾞｲﾄ目：クラス総数。<br>
         * 2ﾊﾞｲﾄ目～ 17 ﾊﾞｲﾄ：クラスコード（EOJの上位 2ﾊﾞｲﾄ）を列挙。<br>
         * <br>
         *
         * @param seoj    受信フレーム内のSEOJ（＝対向ノードのSEOJ）
         * @param strTid  受信フレームのTID値
         * @param strEsv  受信フレームのESV値
         * @param objProp 受信フレーム内のプロパティオブジェクト（EPC,PDC,EDT）
         */
        public void onInformSelfNodeClassListS(ElClassBase seoj, String strTid, String strEsv, ElProp objProp) {
        }

        ;

    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected synchronized String getEdtValueFromApp(String epc) {
        String retEdt = super.getEdtValueFromApp(epc);
        if (retEdt != null) {
            return retEdt;
        }
        switch (epc) {
            case EPC_OPERATION_STATUS:
                return this.getFromAppOperationStatus();
            case EPC_VERSION_INFO:
                return this.getFromAppVersionInfo();
            case EPC_IDENTIFICATION_NUMBER:
                return this.getFromAppIdentificationNumber();
            case EPC_ERROR_CONTENT:
                return this.getFromAppErrorContent();
            case EPC_INDIVIDUAL_IDENTIFICATION_INFO:
                return this.getFromAppIndividualIdentificationInfo();
            case EPC_SELF_NODE_INSTANCE_NUM:
                return this.getFromAppSelfNodeInstanceNum();
            case EPC_SELF_NODE_CLASS_NUM:
                return this.getFromAppSelfNodeClassNum();
            case EPC_INSTANCE_LIST_NOTIFICATION:
                return this.getFromAppInstanceListNotification();
            case EPC_SELF_NODE_INSTANCE_LIST_S:
                return this.getFromAppSelfNodeInstanceListS();
            case EPC_SELF_NODE_CLASS_LIST_S:
                return this.getFromAppSelfNodeClassListS();
            default:
                return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected synchronized boolean isValidEdtValue(ElProp prop) {
        boolean bResult = super.isValidEdtValue(prop);
        if (bResult == true) {
            return true;
        }
        String epc = prop.getStrEpc();
        String edt = prop.getStrEdt();
        switch (epc) {
            case EPC_OPERATION_STATUS:
                return this.isValidEdtOperationStatus(edt);
            case EPC_VERSION_INFO:
                return this.isValidEdtVersionInfo(edt);
            case EPC_IDENTIFICATION_NUMBER:
                return this.isValidEdtIdentificationNumber(edt);
            case EPC_ERROR_CONTENT:
                return this.isValidEdtErrorContent(edt);
            case EPC_INDIVIDUAL_IDENTIFICATION_INFO:
                return this.isValidEdtIndividualIdentificationInfo(edt);
            case EPC_SELF_NODE_INSTANCE_NUM:
                return this.isValidEdtSelfNodeInstanceNum(edt);
            case EPC_SELF_NODE_CLASS_NUM:
                return this.isValidEdtSelfNodeClassNum(edt);
            case EPC_INSTANCE_LIST_NOTIFICATION:
                return this.isValidEdtInstanceListNotification(edt);
            case EPC_SELF_NODE_INSTANCE_LIST_S:
                return this.isValidEdtSelfNodeInstanceListS(edt);
            case EPC_SELF_NODE_CLASS_LIST_S:
                return this.isValidEdtSelfNodeClassListS(edt);
            default:
                return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected synchronized boolean setEdtValueToApp(ElProp prop) {
        boolean bResult = super.setEdtValueToApp(prop);
        if (bResult == true) {
            return true;
        }
        String epc = prop.getStrEpc();
        String edt = prop.getStrEdt();
        switch (epc) {
            case EPC_OPERATION_STATUS:
                return this.setToAppOperationStatus(edt);
            case EPC_INDIVIDUAL_IDENTIFICATION_INFO:
                return this.setToAppIndividualIdentificationInfo(edt);
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
     * Anno                : -<br>
     * Set                 : オプション<br>
     * Get                 : 必須<br>
     * <br>
     * 状変アナウンス      : 必須<br>
     * <br>
     * ノードの動作状態を示す。<br>
     * 起動中 =0x30、未起動中＝0x31<br>
     * <br>
     *
     * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
     */
    protected String getFromAppOperationStatus() {
        return null;
    }

    /**
     * Version情報<br>
     * EPC                 : 0x82<br>
     * データタイプ        : unsigned char×4<br>
     * サイズ              : 4 byte<br>
     * Anno                : -<br>
     * Set                 : -<br>
     * Get                 : 必須<br>
     * <br>
     * 状変アナウンス      : -<br>
     * <br>
     * 通信ミドルウェアが適用している ECHONET Liteの Version、 および通信ミドルウェアがサポートする電文タイプを示す。<br>
     * 1ﾊﾞｲﾄ目：メジャーバョン（小数点以上）をBinaryで示す。<br>
     * 2ﾊﾞｲﾄ目：マイナーバジョン  （小数点 以下 ）をBinaryで示す。<br>
     * 3、4バイト目：電文タイプをビットマップで示す。<br>
     * <br>
     *
     * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
     */
    protected String getFromAppVersionInfo() {
        return EDT_VERSION_INFORMATION;
    }

    /**
     * 識別番号<br>
     * EPC                 : 0x83<br>
     * データタイプ        : unsigned char×17<br>
     * サイズ              : 17 byte<br>
     * Anno                : -<br>
     * Set                 : -<br>
     * Get                 : 必須<br>
     * <br>
     * 状変アナウンス      : -<br>
     * <br>
     * オブジェクトを、ドメイン内で一意に識別するための番号。<br>
     * 1バイト目：下位通信層 ID ﾌｨｰﾙﾄﾞ<br>
     * 0xFE:2～17 バイトをメーカ規定形式により設定<br>
     * その他：future reserved<br>
     * <br>
     *
     * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
     */
    protected String getFromAppIdentificationNumber() {
        return null;
    }

    /**
     * 異常内容<br>
     * EPC                 : 0x89<br>
     * データタイプ        : unsigned short<br>
     * サイズ              : 2 byte<br>
     * Anno                : -<br>
     * Set                 : -<br>
     * Get                 : オプション<br>
     * <br>
     * 状変アナウンス      : -<br>
     * <br>
     * 異常内容<br>
     * 0x0000 ～0x03EC,0x03FF<br>
     * <br>
     *
     * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
     */
    protected String getFromAppErrorContent() {
        return null;
    }

    /**
     * 個体識別情報<br>
     * EPC                 : 0xBF<br>
     * データタイプ        : unsigned short<br>
     * サイズ              : 2 byte<br>
     * Anno                : -<br>
     * Set                 : オプション<br>
     * Get                 : オプション<br>
     * <br>
     * 状変アナウンス      : -<br>
     * <br>
     * 2バイトで指定。<br>
     * 下記 (3)参照。<br>
     * <br>
     *
     * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
     */
    protected String getFromAppIndividualIdentificationInfo() {
        return null;
    }

    /**
     * 自ノードインスタス数<br>
     * EPC                 : 0xD3<br>
     * データタイプ        : unsigned char×3<br>
     * サイズ              : 3 byte<br>
     * Anno                : -<br>
     * Set                 : -<br>
     * Get                 : 必須<br>
     * <br>
     * 状変アナウンス      : -<br>
     * <br>
     * 自ノードで保持するインスタスの総数。<br>
     * 1～3ﾊﾞｲﾄ：インスタ総数<br>
     * <br>
     *
     * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
     */
    protected String getFromAppSelfNodeInstanceNum() {
        int num = this.getNodeBelongsTo().getDeviceEojList().size();
        return ElUtil.intToHexString(num, 3);
    }

    /**
     * 自ノードクラス数<br>
     * EPC                 : 0xD4<br>
     * データタイプ        : unsigned char×2<br>
     * サイズ              : 2 byte<br>
     * Anno                : -<br>
     * Set                 : -<br>
     * Get                 : 必須<br>
     * <br>
     * 状変アナウンス      : -<br>
     * <br>
     * 自ノードで保持するクラス総数<br>
     * 1～2ﾊﾞｲﾄ：クラス総数<br>
     * <br>
     *
     * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
     */
    protected String getFromAppSelfNodeClassNum() {

        // HashSetに格納して重複を削除する。
        HashSet<String> hashSet = new HashSet<String>();
        List<DeviceObjectSuperClass> devices = this.getNodeBelongsTo().getDeviceEojList();
        for (DeviceObjectSuperClass d : devices) {
            hashSet.add(d.getStrClassGroupCode() + d.getStrClassCode());
        }

        int size = hashSet.size() + 1; // NodeProfileを含む数
        return ElUtil.intToHexString(size, 2);
    }

    /**
     * インスタンスリスト通知<br>
     * EPC                 : 0xD5<br>
     * データタイプ        : unsigned char×(MAX)253<br>
     * サイズ              : Max . 253 byte<br>
     * Anno                : 必須<br>
     * Set                 : -<br>
     * Get                 : -<br>
     * <br>
     * 状変アナウンス      : 必須<br>
     * <br>
     * 自ノード内インスタンスに構成変化があった時のインスタンスリスト<br>
     * 1ﾊﾞｲﾄ目：通報インスタ数<br>
     * 2ﾊﾞｲﾄ目～253 ﾊﾞｲﾄ：ECHONETオブジェクトコード（ EOJ3バイ ト）を列挙。<br>
     * <br>
     *
     * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
     */
    protected String getFromAppInstanceListNotification() {

        // memo : 列挙されるインスタンスには、ノードプロファイルオブジェクトは含まないものとする。
        // 「ECHONET-Lite_Ver.1.13_02.pdf」(6-10) より

        List<DeviceObjectSuperClass> lstDevice = this.getNodeBelongsTo().getDeviceEojList();
        String retStr = "";
        for (DeviceObjectSuperClass eoj : lstDevice) {
            retStr += eoj.getStrEojCode();
        }
        String hexStrCnt = ElUtil.intToHexString(lstDevice.size());
        retStr = hexStrCnt + retStr; // カウント部分とEOJ部分を連結

        ElLog.d(TAG, "getFromAppInstanceListNotification", false);
        return retStr;
    }

    /**
     * 自ノードインスタスリトS<br>
     * EPC                 : 0xD6<br>
     * データタイプ        : unsigned char× (MAX)253<br>
     * サイズ              :  Max .253 byte<br>
     * Anno                : -<br>
     * Set                 : -<br>
     * Get                 : 必須<br>
     * <br>
     * 状変アナウンス      : -<br>
     * <br>
     * 自ノード内インスタリト<br>
     * 1ﾊﾞｲﾄ目：インスタンス総数。<br>
     * 2～253ﾊﾞｲﾄ目：ECHONETオ ブジェクトコード（EOJ 3バイ ト）を列挙。<br>
     * <br>
     *
     * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
     */
    protected String getFromAppSelfNodeInstanceListS() {
        List<DeviceObjectSuperClass> lstDevice = this.getNodeBelongsTo().getDeviceEojList();
        String retStr = "";
        for (DeviceObjectSuperClass eoj : lstDevice) {
            retStr += eoj.getStrEojCode();
        }
        String hexStrCnt = ElUtil.intToHexString(lstDevice.size());
        retStr = hexStrCnt + retStr; // カウント部分とEOJ部分を連結

        ElLog.d(TAG, "getFromAppSelfNodeInstanceListS", false);
        return retStr;
    }

    /**
     * 自ノードクラスリストS<br>
     * EPC                 : 0xD7<br>
     * データタイプ        : unsigned char×(MAX)17<br>
     * サイズ              : 17 byte<br>
     * Anno                : -<br>
     * Set                 : -<br>
     * Get                 : 必須<br>
     * <br>
     * 状変アナウンス      : -<br>
     * <br>
     * 自ノード内クラスリスト<br>
     * 1ﾊﾞｲﾄ目：クラス総数。<br>
     * 2ﾊﾞｲﾄ目～ 17 ﾊﾞｲﾄ：クラスコード（EOJの上位 2ﾊﾞｲﾄ）を列挙。<br>
     * <br>
     *
     * @return EDT値（16進数文字列）、値がない場合はnulllを戻す
     */
    protected String getFromAppSelfNodeClassListS() {
        HashSet<String> hashSet = new HashSet<String>();
        List<DeviceObjectSuperClass> devices = this.getNodeBelongsTo().getDeviceEojList();
        for (DeviceObjectSuperClass d : devices) {
            hashSet.add(d.getStrClassGroupCode() + d.getStrClassCode());
        }
        int size = hashSet.size(); // デバイスオブジェクトの数

        int s = (size >= 9) ? 8 : size; // 最大8種類まで
        String strEdt = "";
        strEdt += ElUtil.intToHexString(size);

        Iterator<String> itr = hashSet.iterator();
        for (int i = 0; i < s; i++) {
            strEdt += itr.next();
        }
        return strEdt;
    }

    // アプリケーションから取得した値の妥当性を検証するメソッド群

    /**
     * 動作状態<br>
     * EPC                 : 0x80<br>
     * データタイプ        : unsigned char<br>
     * サイズ              : 1 byte<br>
     * Anno                : -<br>
     * Set                 : オプション<br>
     * Get                 : 必須<br>
     * <br>
     * 状変アナウンス      : 必須<br>
     * <br>
     * ノードの動作状態を示す。<br>
     * 起動中 =0x30、未起動中＝0x31<br>
     * <br>
     *
     * @param edt EDT値（16進数文字列）
     * @return true: Valid , false Invalid
     */
    protected boolean isValidEdtOperationStatus(String edt) {
        if (edt == null || !(edt.length() == 1 * 2)) return false;
        if (!edt.equalsIgnoreCase(EDT_OPERATION_STATUS_ON) && !edt.equalsIgnoreCase(EDT_OPERATION_STATUS_OFF))
            return false;
        return true;
    }

    /**
     * Version情報<br>
     * EPC                 : 0x82<br>
     * データタイプ        : unsigned char×4<br>
     * サイズ              : 4 byte<br>
     * Anno                : -<br>
     * Set                 : -<br>
     * Get                 : 必須<br>
     * <br>
     * 状変アナウンス      : -<br>
     * <br>
     * 通信ミドルウェアが適用している ECHONET Liteの Version、 および通信ミドルウェアがサポートする電文タイプを示す。<br>
     * 1ﾊﾞｲﾄ目：メジャーバョン（小数点以上）をBinaryで示す。<br>
     * 2ﾊﾞｲﾄ目：マイナーバジョン  （小数点 以下 ）をBinaryで示す。<br>
     * 3、4バイト目：電文タイプをビットマップで示す。<br>
     * <br>
     *
     * @param edt EDT値（16進数文字列）
     * @return true: Valid , false Invalid
     */
    protected boolean isValidEdtVersionInfo(String edt) {
        if (edt == null || !(edt.length() == 4 * 2)) return false;
        return true;
    }

    /**
     * 識別番号<br>
     * EPC                 : 0x83<br>
     * データタイプ        : unsigned char×17<br>
     * サイズ              : 17 byte<br>
     * Anno                : -<br>
     * Set                 : -<br>
     * Get                 : 必須<br>
     * <br>
     * 状変アナウンス      : -<br>
     * <br>
     * オブジェクトを、ドメイン内で一意に識別するための番号。<br>
     * 1バイト目：下位通信層 ID ﾌｨｰﾙﾄﾞ<br>
     * 0xFE:2～17 バイトをメーカ規定形式により設定<br>
     * その他：future reserved<br>
     * <br>
     *
     * @param edt EDT値（16進数文字列）
     * @return true: Valid , false Invalid
     */
    protected boolean isValidEdtIdentificationNumber(String edt) {
        if (edt == null || !(edt.length() == 17 * 2)) return false;
        return true;
    }

    /**
     * 異常内容<br>
     * EPC                 : 0x89<br>
     * データタイプ        : unsigned short<br>
     * サイズ              : 2 byte<br>
     * Anno                : -<br>
     * Set                 : -<br>
     * Get                 : オプション<br>
     * <br>
     * 状変アナウンス      : -<br>
     * <br>
     * 異常内容<br>
     * 0x0000 ～0x03EC,0x03FF<br>
     * <br>
     *
     * @param edt EDT値（16進数文字列）
     * @return true: Valid , false Invalid
     */
    protected boolean isValidEdtErrorContent(String edt) {
        if (edt == null || !(edt.length() == 2 * 2)) return false;
        if (!(ElUtil.compElUnsignedShort("0000", edt) <= 0 && ElUtil.compElUnsignedShort(edt, "03EC") <= 0 || ElUtil.compElUnsignedShort(edt, "03FF") == 0))
            return false;
        return true;
    }

    /**
     * 個体識別情報<br>
     * EPC                 : 0xBF<br>
     * データタイプ        : unsigned short<br>
     * サイズ              : 2 byte<br>
     * Anno                : -<br>
     * Set                 : オプション<br>
     * Get                 : オプション<br>
     * <br>
     * 状変アナウンス      : -<br>
     * <br>
     * 2バイトで指定。<br>
     * 下記 (3)参照。<br>
     * <br>
     *
     * @param edt EDT値（16進数文字列）
     * @return true: Valid , false Invalid
     */
    protected boolean isValidEdtIndividualIdentificationInfo(String edt) {
        if (edt == null || !(edt.length() == 2 * 2)) return false;
        return true;
    }

    /**
     * 自ノードインスタス数<br>
     * EPC                 : 0xD3<br>
     * データタイプ        : unsigned char×3<br>
     * サイズ              : 3 byte<br>
     * Anno                : -<br>
     * Set                 : -<br>
     * Get                 : 必須<br>
     * <br>
     * 状変アナウンス      : -<br>
     * <br>
     * 自ノードで保持するインスタスの総数。<br>
     * 1～3ﾊﾞｲﾄ：インスタ総数<br>
     * <br>
     *
     * @param edt EDT値（16進数文字列）
     * @return true: Valid , false Invalid
     */
    protected boolean isValidEdtSelfNodeInstanceNum(String edt) {
        if (edt == null || !(edt.length() == 3 * 2)) return false;
        return true;
    }

    /**
     * 自ノードクラス数<br>
     * EPC                 : 0xD4<br>
     * データタイプ        : unsigned char×2<br>
     * サイズ              : 2 byte<br>
     * Anno                : -<br>
     * Set                 : -<br>
     * Get                 : 必須<br>
     * <br>
     * 状変アナウンス      : -<br>
     * <br>
     * 自ノードで保持するクラス総数<br>
     * 1～2ﾊﾞｲﾄ：クラス総数<br>
     * <br>
     *
     * @param edt EDT値（16進数文字列）
     * @return true: Valid , false Invalid
     */
    protected boolean isValidEdtSelfNodeClassNum(String edt) {
        if (edt == null || !(edt.length() == 2 * 2)) return false;
        return true;
    }

    /**
     * インスタンスリスト通知<br>
     * EPC                 : 0xD5<br>
     * データタイプ        : unsigned char×(MAX)253<br>
     * サイズ              : Max . 253 byte<br>
     * Anno                : 必須<br>
     * Set                 : -<br>
     * Get                 : -<br>
     * <br>
     * 状変アナウンス      : 必須<br>
     * <br>
     * 自ノード内インスタンスに構成変化があった時のインスタンスリスト<br>
     * 1ﾊﾞｲﾄ目：通報インスタ数<br>
     * 2ﾊﾞｲﾄ目～253 ﾊﾞｲﾄ：ECHONETオブジェクトコード（ EOJ3バイ ト）を列挙。<br>
     * <br>
     *
     * @param edt EDT値（16進数文字列）
     * @return true: Valid , false Invalid
     */
    protected boolean isValidEdtInstanceListNotification(String edt) {
        if (edt == null || !(edt.length() <= 253 * 2)) return false;
        return true;
    }

    /**
     * 自ノードインスタスリトS<br>
     * EPC                 : 0xD6<br>
     * データタイプ        : unsigned char× (MAX)253<br>
     * サイズ              :  Max .253 byte<br>
     * Anno                : -<br>
     * Set                 : -<br>
     * Get                 : 必須<br>
     * <br>
     * 状変アナウンス      : -<br>
     * <br>
     * 自ノード内インスタリト<br>
     * 1ﾊﾞｲﾄ目：インスタンス総数。<br>
     * 2～253ﾊﾞｲﾄ目：ECHONETオ ブジェクトコード（EOJ 3バイ ト）を列挙。<br>
     * <br>
     *
     * @param edt EDT値（16進数文字列）
     * @return true: Valid , false Invalid
     */
    protected boolean isValidEdtSelfNodeInstanceListS(String edt) {
        if (edt == null || !(edt.length() <= 253 * 2)) return false;
        return true;
    }

    /**
     * 自ノードクラスリストS<br>
     * EPC                 : 0xD7<br>
     * データタイプ        : unsigned char×(MAX)17<br>
     * サイズ              : 17 byte<br>
     * Anno                : -<br>
     * Set                 : -<br>
     * Get                 : 必須<br>
     * <br>
     * 状変アナウンス      : -<br>
     * <br>
     * 自ノード内クラスリスト<br>
     * 1ﾊﾞｲﾄ目：クラス総数。<br>
     * 2ﾊﾞｲﾄ目～ 17 ﾊﾞｲﾄ：クラスコード（EOJの上位 2ﾊﾞｲﾄ）を列挙。<br>
     * <br>
     *
     * @param edt EDT値（16進数文字列）
     * @return true: Valid , false Invalid
     */
    protected boolean isValidEdtSelfNodeClassListS(String edt) {
        if (edt == null || !(edt.length() <= 17 * 2)) return false;
        return true;
    }

    // アプリケーションの設置する値の妥当性を検証するメソッド群

    /**
     * 動作状態<br>
     * EPC                 : 0x80<br>
     * データタイプ        : unsigned char<br>
     * サイズ              : 1 byte<br>
     * Anno                : -<br>
     * Set                 : オプション<br>
     * Get                 : 必須<br>
     * <br>
     * 状変アナウンス      : 必須<br>
     * <br>
     * ノードの動作状態を示す。<br>
     * 起動中 =0x30、未起動中＝0x31<br>
     * <br>
     *
     * @param edt EDT値（16進数文字列）
     * @return true: Success , false Failure
     */
    protected boolean setToAppOperationStatus(String edt) {
        return false;
    }

    /**
     * 個体識別情報<br>
     * EPC                 : 0xBF<br>
     * データタイプ        : unsigned short<br>
     * サイズ              : 2 byte<br>
     * Anno                : -<br>
     * Set                 : オプション<br>
     * Get                 : オプション<br>
     * <br>
     * 状変アナウンス      : -<br>
     * <br>
     * 2バイトで指定。<br>
     * 下記 (3)参照。<br>
     * <br>
     *
     * @param edt EDT値（16進数文字列）
     * @return true: Success , false Failure
     */
    protected boolean setToAppIndividualIdentificationInfo(String edt) {
        return false;
    }


    /**
     * EPC名称マップ
     */
    public static Map<String, String> MAP_EPC_NAME = new HashMap<String, String>() {
        {
            put("80", "動作状態");
            put("82", "Version情報");
            put("83", "識別番号");
            put("89", "異常内容");
            put("BF", "個体識別番号");
            put("D3", "自ノードインスタンス数");
            put("D4", "自ノードクラス数");
            put("D5", "インスタンスリスト通知");
            put("D6", "自ノードインスタンスリスト S");
            put("D7", "自ノードクラスリスト S");
        }
    };

}
