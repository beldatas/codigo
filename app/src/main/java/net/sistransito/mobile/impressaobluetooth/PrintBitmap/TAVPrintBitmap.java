package net.sistransito.mobile.impressaobluetooth.PrintBitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;

import net.sistransito.mobile.appconstantes.AppConstants;
import net.sistransito.mobile.appobjeto.AppObject;
import net.sistransito.mobile.autoinfracao.DadosDoAuto;
import net.sistransito.mobile.impressaobluetooth.PrintBitmap.base.BasePrintBitmap;
import net.sistransito.mobile.impressaobluetooth.PrintBitmap.base.PrintBitmapFormat;
import net.sistransito.mobile.tav.TAVData;
import net.sistransito.mobile.util.User;

/**
 * Created by GAZI_RIMON on 8/15/2016.
 */
public class TAVPrintBitmap extends BasePrintBitmap {
    private DadosDoAuto aData;
    private TAVData tavData;

    public TAVPrintBitmap(Context context, TAVData tavData, DadosDoAuto autoData) {
        super(context);
        this.aData=autoData;
        this.tavData=tavData;
    }

    @Override
    public Bitmap getBitmap() {
        PrintBitmapFormat bitmapFormat = new PrintBitmapFormat(context);

        String estrutura = "Cabeça de alavanca - " + tavData.getCabeca_de_alavanca() + "\n" +
                "Cabeça de alavanca - " + tavData.getCabeca_de_alavanca() + "\n" +
                "Volante - " + tavData.getVolante();

        String acessorios = "Antena de rádio - " + tavData.getRadio() + "\n" +
                "Bagageiro - " + tavData.getBagageiro() + "\n" +
                "Triângulo - " + tavData.getTriangulo();

        String legendaTextLeft = "AM - Amongado\n" +
                "OK - Funcionando\n" +
                "TR - Trincado";

        String legendaTextCenter = "NT - Não tem\n" +
                "IN - Inoperante\n" +
                "TB - Desbotado";

        String legendaTextRight = "QB - Quebrado\n" +
                "RC - Riscado\n" +
                "RG - Rasgado";

        String title = "GOVERNO DO ESTADO DO PARÁ\n" +
                "SECRETARIA DE ESTADO DE SEGURANÇA PÚBLICA \n" +
                "DEPARTAMENTO DE TRÂNSITO DO ESTADO DO PARÁ";
        String subTitle = "TERMO DE APREENSÃO DE VEÍCULO (TAV)";
//
//        bitmapFormat.createTitle(title, subTitle, "ic_left_title.png", "ic_right_title.png");
//        bitmapFormat.createQuotes("", Paint.Align.LEFT, true, false);
//        bitmapFormat.createTableIdentificacao("NÚMERO DO AIT", aData.getNumero_auto(), "NÚMERO DO TAV", tavData.getNumero_tav(), false, PrintBitmapFormat.TableCellAlign.MIDDLE);
//        bitmapFormat.createQuotes("MOTIVO PARA O RECOLHIMENTO", aData.getAmparo_legal().toUpperCase(), true);
//
//        bitmapFormat.createQuotes("IDENTIFICAÇÃO DO LOCAL, DATA E HORA", Paint.Align.LEFT, true, false);
//        bitmapFormat.createQuotes("LOCAL", aData.getLocal(), false);
//        bitmapFormat.createTable("DATA", aData.getData(), "HORA", aData.getHora(), "CÓD. MUNICÍPIO", aData.getCodigo_do_municipio(), "MUNICÍPIO/UF", aData.getMunicipio() + "/" + aData.getUf(), true);
//
//        bitmapFormat.createQuotes("IDENTIFICAÇÃO DO CONDUTOR", Paint.Align.LEFT, true, false);
//        bitmapFormat.createQuotes("NOME", aData.getNome_do_Condutor(), false);
//        bitmapFormat.createTable("REGISTRO CNH/PPD/ACC", aData.getCnh_ppd(), "UF", aData.getUf_cnh(), aData.getTipo_de_documento(), aData.getNumero_documento(), true);
//
//        bitmapFormat.createQuotes("2-IDENTIFICAÇÃO DO VEÍCULO", Paint.Align.LEFT, true, false);
//        bitmapFormat.createTable("PLACA", aData.getPlate(), "UF", aData.getUf(), "PAIS", aData.getPais(), "CHASSI", aData.getChassi(), false);
//        bitmapFormat.createTable("MARCA/MODELO", aData.getModel(), "ESPÉCIE", aData.getEspecie(), true, PrintBitmapFormat.TableCellAlign.MIDDLE);
//
//        bitmapFormat.createQuotes("CONDIÇÕES DO VEÍCULO NO ATO DA REMOÇÃO", Paint.Align.LEFT, true, false);
//        bitmapFormat.createTableTav("QUANTO À ESTRUTURA", estrutura, "QUANTOS AOS ACESSÓRIOS", acessorios, true, PrintBitmapFormat.TableCellAlign.MIDDLE);
//
//        bitmapFormat.createQuotes("LEGENDAS", Paint.Align.LEFT, true, false);
//        bitmapFormat.createTable("", legendaTextLeft, "", legendaTextCenter, "", legendaTextRight, true);
//
//        bitmapFormat.createQuotesObs("OBSERVAÇÕES", tavData.getObservacao(), true);
//        bitmapFormat.createTable("OCÔMETRO", tavData.getOdometro(), "COMBUSTÍVEL", tavData.getOdometro(), "PARQUE DE RETENÇÃO", user.getNome_orgao(), "REMOVIDO ATRAVÉS DE", tavData.getRemocao_atraves_de(), true);
//
//        if (tavData.getRemocao_atraves_de().contains("guincho")){
//            bitmapFormat.createTable("EMPRESA", tavData.getNome_da_empresa(), "CONDUTOR DO GUINCHO", tavData.getNome_do_condutor_do_guincho(), false, PrintBitmapFormat.TableCellAlign.RIGHT);
//            bitmapFormat.createQuotesAssinatura("ASSINATURA", "\n\n\n", true);
//        } else {
//            bitmapFormat.createTableName("NOME", user.getNome().toUpperCase(), "MATRÍCULA", user.getMatricula(), false, PrintBitmapFormat.TableCellAlign.RIGHT);
//        }
//
//        bitmapFormat.createQuotes("IDENTIFICAÇÃO DA AUTORIDADE OU AGENTE AUTUADOR", Paint.Align.LEFT, true, false);
//        bitmapFormat.createTableName("NOME", user.getNome().toUpperCase(), "MATRÍCULA", user.getMatricula(), false, PrintBitmapFormat.TableCellAlign.RIGHT);
//        bitmapFormat.createQuotesAssinatura("ASSINATURA", "\n\n\n", true);

        bitmapFormat.setNewLine(2);

        bitmapFormat.printDocumnetClose();
        bitmapFormat.saveBitmap();
        return bitmapFormat.getPrintBitmap();
    }
}

