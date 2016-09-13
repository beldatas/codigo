package net.sistransito.mobile.impressaobluetooth.PrintBitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;

import net.sistransito.mobile.appconstantes.AppConstants;
import net.sistransito.mobile.appobjeto.AppObject;
import net.sistransito.mobile.autoinfracao.DadosDoAuto;
import net.sistransito.mobile.impressaobluetooth.PrintBitmap.base.BasePrintBitmap;
import net.sistransito.mobile.impressaobluetooth.PrintBitmap.base.PrintBitmapFormat;
import net.sistransito.mobile.rrd.RRDData;
import net.sistransito.mobile.util.User;

/**
 * Created by GAZI_RIMON on 8/15/2016.
 */
public class RRDPrintBitmap extends BasePrintBitmap {
    private DadosDoAuto aData;
    private RRDData rrdData;
    public RRDPrintBitmap(Context context,RRDData rrdData, DadosDoAuto autoData) {
        super(context);
        this.aData=autoData;
        this.rrdData=rrdData;
    }
    @Override
    public Bitmap getBitmap() {
        PrintBitmapFormat bitmapFormat = new PrintBitmapFormat(context);

        String title = "GOVERNO DO ESTADO DO PARÁ\n" +
                "SECRETARIA DE ESTADO DE SEGURANÇA PÚBLICA \n" +
                "DEPARTAMENTO DE TRÂNSITO DO ESTADO DO PARÁ";
        String subTitle = "RECIBO DE RECOLHIMENTO DE DOCUMENTOS (RRD)";

        bitmapFormat.createTitle(title, subTitle, "ic_left_title.png", "ic_right_title.png");
        bitmapFormat.createQuotes("IDENTIFICAÇÃO DO RECIBO", Paint.Align.LEFT, true, false);
        bitmapFormat.createTableIdentificacao("NÚMERO DO AIT", aData.getNumero_auto(), "NÚMERO DO RRD", rrdData.getNumero_rrd(), false, PrintBitmapFormat.TableCellAlign.MIDDLE);

        bitmapFormat.createQuotes("IDENTIFICAÇÃO DO VEÍCULO", Paint.Align.LEFT, true, false);
        bitmapFormat.createTable("PLACA", rrdData.getPlaca(), "MARCA/MODELO", aData.getModel().toUpperCase(), true, PrintBitmapFormat.TableCellAlign.MIDDLE);

        bitmapFormat.createQuotes("RECEBEMOS DE", Paint.Align.LEFT, true, false);
        bitmapFormat.createQuotes("NOME", rrdData.getNome_do_condutor(), true);

        bitmapFormat.createQuotes("DOCUMENTO ABAIXO ESPECIFICADO", Paint.Align.LEFT, true, false);
        bitmapFormat.createTable("REGISTRO CNH/PPD/ACC", rrdData.getN_registro(), "VALIDADE", rrdData.getValidade(), "UF", aData.getUf_cnh(), false);
        bitmapFormat.createQuotes("MOTIVO PARA O RECOLHIMENTO", rrdData.getMotivo_para_recolhimento().toUpperCase(), true);

        bitmapFormat.createQuotes("DATA DO RECOLHIMENTO", Paint.Align.LEFT, true, false);
        bitmapFormat.createTable("DATA", aData.getData(), "HORA", aData.getHora(), "MUNICÍPIO", aData.getMunicipio(), "UF", aData.getUf(), false);

        bitmapFormat.createQuotes("IDENTIFICAÇÃO DA AUTORIDADE OU AGENTE AUTUADOR", Paint.Align.LEFT, true, false);
        bitmapFormat.createTableName("NOME", user.getNome().toUpperCase(), "MATRÍCULA", user.getMatricula(), false, PrintBitmapFormat.TableCellAlign.RIGHT);
        bitmapFormat.createQuotesAssinatura("ASSINATURA", "\n\n\n", true);

        bitmapFormat.setNewLine(2);
        bitmapFormat.createQuotes("ORIENTAÇÕES AO USUÁRIO", Paint.Align.CENTER);
        bitmapFormat.createQuotes("A partir desta data, o condutor/proprietário tem " + rrdData.getQtd_de_dias_para_regularizacao() + " dias úteis para, " +
                "providenciar a regularização do veículo, conforme o amparo_legal. 270, §§ 2º e 3º, " +
                "Lei 9.503/97, o qual ficará impedido de transitar além dos limites " +
                "necessários à resolução do(s) problema(s), sob pena de enquadramento " +
                "no amparo_legal. 232 da lei 9.503/97. O(s) domumento(s) recolhidos(s) devem(m) ser " +
                "procurado(s), mediante a apresentação do veículo regularizado, na vistoria" +
                "do Detran, sendo imprescindível a apresentação deste recibo.", true);

        bitmapFormat.setNewLine(20);
        bitmapFormat.printDocumnetClose();
        bitmapFormat.saveBitmap();
        return bitmapFormat.getPrintBitmap();
    }
}

