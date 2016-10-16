package net.sistransito.mobile.impressaobluetooth.PrintBitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;

import net.sistransito.mobile.autoinfracao.DadosDoAuto;
import net.sistransito.mobile.impressaobluetooth.PrintBitmap.base.BasePrintBitmap;
import net.sistransito.mobile.impressaobluetooth.PrintBitmap.base.PrintBitmapFormat;

/**
 * Created by GAZI_RIMON on 8/15/2016.
 */
public class AutoDePrintBitmap extends BasePrintBitmap {
    private DadosDoAuto aData;

    public AutoDePrintBitmap(Context context, DadosDoAuto autoData) {
        super(context);
        this.aData = autoData;
    }

    @Override
    public Bitmap getBitmap() {

        String textoDescricao;
        PrintBitmapFormat bitmapFormat = new PrintBitmapFormat(context);
        String title = "GOVERNO DO ESTADO DO PARÁ\n" +
                "SECRETARIA DE ESTADO DE SEGURANÇA PÚBLICA \n" +
                "DEPARTAMENTO DE TRÂNSITO DO ESTADO DO PARÁ";
        String subTitle = "AUTO DE INFRAÇÃO DE TRÂNSITO (AIT)";

        bitmapFormat.createTitle(title, subTitle, "ic_left_title.png", "ic_right_title.png", PrintBitmapFormat.TITLE_FONT_SIZE, PrintBitmapFormat.SUB_TITLE_FONT_SIZE);
        bitmapFormat.createQuotes("1-IDENTIFICAÇÃO DA AUTUAÇÃO", Paint.Align.LEFT, true, false, PrintBitmapFormat.SUB_TITLE_FONT_SIZE);

        bitmapFormat.createTitle("ÓRGÃO AUTUADOR", user.getCodigo_orgao(), "NÚMERO DO AIT", aData.getNumero_auto(), PrintBitmapFormat.NORMAL_FONT,
                PrintBitmapFormat.MAIOR_FONT);

        bitmapFormat.createQuotes("2-IDENTIFICAÇÃO DO VEÍCULO", Paint.Align.LEFT, true, false, PrintBitmapFormat.SUB_TITLE_FONT_SIZE);
        bitmapFormat.createTable("PLACA", aData.getPlate(), "UF",
                aData.getUf(), "PAIS", aData.getPais(),
                "CHASSI", aData.getChassi(), false,
                PrintBitmapFormat.NORMAL_FONT, PrintBitmapFormat.MEDIO_FONT);

        bitmapFormat.createTable("MARCA/MODELO", aData.getModel(),
                "ESPÉCIE", aData.getEspecie(),
                true, PrintBitmapFormat.TableCellAlign.MIDDLE,
                PrintBitmapFormat.NORMAL_FONT, PrintBitmapFormat.MEDIO_FONT);

        bitmapFormat.createQuotes("3-IDENTIFICAÇÃO DO CONDUTOR", Paint.Align.LEFT, true, false, PrintBitmapFormat.SUB_TITLE_FONT_SIZE);
        bitmapFormat.createQuotes("NOME", aData.getNome_do_Condutor(), false, false, PrintBitmapFormat.MEDIO_FONT);

        bitmapFormat.createTable("REGISTRO CNH/PPD/ACC", aData.getCnh_ppd(), "UF", aData.getUf_cnh(), aData.getTipo_de_documento(), aData.getNumero_documento(), true,
                PrintBitmapFormat.NORMAL_FONT, PrintBitmapFormat.MEDIO_FONT);
//
        bitmapFormat.createQuotes("4-IDENTIFICAÇÃO DO LOCAL, DATA E HORA", Paint.Align.LEFT, true, false, PrintBitmapFormat.SUB_TITLE_FONT_SIZE);
        bitmapFormat.createQuotes("LOCAL", aData.getLocal(), false, false, PrintBitmapFormat.MEDIO_FONT);

        bitmapFormat.createTable("DATA", aData.getData(), "HORA", aData.getHora(), "CÓD. MUNICÍPIO",
                aData.getCodigo_do_municipio(), "MUNICÍPIO/UF",
                aData.getMunicipio() + "/" + aData.getUf(),
                true, PrintBitmapFormat.NORMAL_FONT, PrintBitmapFormat.MEDIO_FONT);

        bitmapFormat.createQuotes("5-TIPIFICAÇÃO DA INFRAÇÃO", Paint.Align.LEFT, true, false, PrintBitmapFormat.SUB_TITLE_FONT_SIZE);
        bitmapFormat.createQuotes("DESCRIÇÃO DA INFRAÇÃO", aData.getInfracao(), false, false, PrintBitmapFormat.NORMAL_FONT);

        bitmapFormat.createTable("CÓD. INFRAÇÃO", aData.getEnquadra(),
                "DESDOBRAMENTO", aData.getDesdob(),
                "AMPARO LEGAL", aData.getAmparo_legal(), true, PrintBitmapFormat.NORMAL_FONT, PrintBitmapFormat.MEDIO_FONT);

        bitmapFormat.createQuotes("EQUIPAMENTOS/INSTRUMENTOS DE AFERIÇÃO UTILIZADO", Paint.Align.LEFT, true, false, PrintBitmapFormat.SUB_TITLE_FONT_SIZE);

        if (aData.getDescricao().contains("Selecione")) {
            textoDescricao = "";
        } else {
            textoDescricao = aData.getDescricao();
        }

        bitmapFormat.createTable("DESCRIÇÃO", textoDescricao, "MARCA", aData.getMarca(), true, PrintBitmapFormat.TableCellAlign.MIDDLE, PrintBitmapFormat.NORMAL_FONT, PrintBitmapFormat.MEDIO_FONT);

        bitmapFormat.createTable("MODELO", aData.getModelo(), "NÚMERO DE SÉRIE", aData.getNumero_de_serie(), true, PrintBitmapFormat.TableCellAlign.MIDDLE, PrintBitmapFormat.NORMAL_FONT, PrintBitmapFormat.MEDIO_FONT);

        if (aData.getDescricao().contains("Etilometro")) {
            bitmapFormat.createTable("MEDIÇÃO REALIZADA", aData.getMedicao_realizada() + " Mg/L",
                    "LIMITE REGULAMENTADO", "0,00 Mg/L",
                    true, PrintBitmapFormat.TableCellAlign.MIDDLE,
                    PrintBitmapFormat.NORMAL_FONT, PrintBitmapFormat.MEDIO_FONT);

            bitmapFormat.createTable("VALOR CONSIDERADO", aData.getValor_considerada() + " Mg/L",
                    "NÚMERO DA AMOSTRA", aData.getN_da_amostra(),
                    true, PrintBitmapFormat.TableCellAlign.MIDDLE, PrintBitmapFormat.NORMAL_FONT, PrintBitmapFormat.MEDIO_FONT);
        } else {
            bitmapFormat.createTable("MEDIÇÃO REALIZADA", aData.getMedicao_realizada(),
                    "LIMITE REGULAMENTADO", "", true,
                    PrintBitmapFormat.TableCellAlign.MIDDLE,
                    PrintBitmapFormat.NORMAL_FONT, PrintBitmapFormat.MEDIO_FONT);

            bitmapFormat.createTable("VALOR CONSIDERADO", aData.getValor_considerada(),
                    "NÚMERO DA AMOSTRA/TCA", aData.getN_da_amostra(),
                    true, PrintBitmapFormat.TableCellAlign.MIDDLE,
                    PrintBitmapFormat.NORMAL_FONT, PrintBitmapFormat.MEDIO_FONT);
        }

        bitmapFormat.createQuotes("PROCEDIMENTOS", Paint.Align.LEFT, true, false, PrintBitmapFormat.SUB_TITLE_FONT_SIZE);
        bitmapFormat.createQuotes("MEDIDAS ADMINISTRATIVAS", aData.getProcedimentos(), true, false, PrintBitmapFormat.MEDIO_FONT);
        bitmapFormat.createQuotesObs("OBSERVAÇÕES", aData.getObservacao(), true, PrintBitmapFormat.MEDIO_FONT);

        bitmapFormat.createQuotes("6-IDENTIFICAÇÃO DA AUTORIDADE OU AGENTE AUTUADOR", Paint.Align.LEFT, true, false, PrintBitmapFormat.SUB_TITLE_FONT_SIZE);
        bitmapFormat.createTableName("NOME", user.getNome().toUpperCase(), "MATRÍCULA", user.getMatricula(), false, PrintBitmapFormat.TableCellAlign.RIGHT,
                PrintBitmapFormat.NORMAL_FONT, PrintBitmapFormat.MEDIO_FONT);

        bitmapFormat.createQuotesAssinatura("ASSINATURA", "\n\n\n", true, PrintBitmapFormat.NORMAL_FONT);

        bitmapFormat.createQuotes("7-IDENTIFICAÇÃO DO EMBARCADOR OU EXPEDIDOR", Paint.Align.LEFT, true, false, PrintBitmapFormat.SUB_TITLE_FONT_SIZE);
        bitmapFormat.createTableName("NOME", aData.getIdetificacao_embarcador(), "CNPJ/CPF", aData.getCnpj_cpf_embarcador(), true, PrintBitmapFormat.TableCellAlign.RIGHT,
                PrintBitmapFormat.NORMAL_FONT, PrintBitmapFormat.MEDIO_FONT);

        bitmapFormat.createQuotes("8-IDENTIFICAÇÃO DO TRANSPORTADOR", Paint.Align.LEFT, true, false, PrintBitmapFormat.SUB_TITLE_FONT_SIZE);
        bitmapFormat.createTableName("NOME", aData.getIdentificacao_do_transportador(), "CNPJ/CPF", aData.getCnpj_cpf_transportador(),
                true, PrintBitmapFormat.TableCellAlign.RIGHT
                , PrintBitmapFormat.NORMAL_FONT, PrintBitmapFormat.MEDIO_FONT);

        bitmapFormat.createQuotes("9-ASSINATURA DO INFRATOR OU CONDUTOR", Paint.Align.LEFT, true, false, PrintBitmapFormat.SUB_TITLE_FONT_SIZE);
        bitmapFormat.createQuotesAssinatura("\n\n\n", "\n\n\n", true, PrintBitmapFormat.NORMAL_FONT);
/**
 bitmapFormat.setNewLine(2);
 bitmapFormat.createQuotes("ORIENTAÇÕES AO USUÁRIO", Paint.Align.CENTER);
 bitmapFormat.setNewLine(2);
 bitmapFormat.createQuotes("1 - DEFESA DE AUTUAÇÃO", false);
 bitmapFormat.createQuotes("O prazo para a apresentação da Defesa de Autuação é de 15 (quinze) dias, " +
 "contados a partir da aData do recebimento da Notificação da Autuação caso o Agente de " +
 "Trânsito não faça a abordagem no ato da infração, que deverá ser encaminhada ao Órgão " +
 "de Trânsito responsável pela Autuação (artigo 3º, § 2º da Resolução de nº 149/2003 do " +
 "Conselho Nacional de Trânsito - CONTRAN).", false);
 bitmapFormat.setNewLine(3);
 bitmapFormat.createQuotes("Em caso de abordagem e a infração for típica de condutor e este assine o " +
 "Auto de Infração de Trânsito - AIT, o prazo para a apresentação da Defesa de Autuação será " +
 "de 15 (quinze) dias, contados a partir da aData da infração, uma vez que o AIT valerá com " +
 "Notificação de Autuação, da mesma forma quando a infração for de responsabilidade do " +
 "proprietário e este estiver na condução e assinar o AIT", false);

 bitmapFormat.setNewLine(3);
 bitmapFormat.createQuotes("2- DOCUMENTAÇÃO", false);
 bitmapFormat.createQuotes("Para interposição de defesa de autuação, faz-se necessário apresentar, \n" +
 "no mínimo, os seguintes documentos: requerimento com as razões da defesa, assinado; original " +
 "ou cópia do auto de infração; certificado de registro e licenciamento anual - CRLV; cópia do " +
 "documento oficial de identidade, com foto e assinado; procuração, com firma reconheciada, " +
 "quando se fizer representar por terceiros; em se tratando de Pessoa Jurídica, cópia do " +
 "Estatuto ou Contrato Social e de sua última alteraçaõ, se houver; provas documentais " +
 "previstas em lei que comprovem as alegações apresentadas.", false);

 bitmapFormat.setNewLine(3);
 bitmapFormat.createQuotes("3- PROTOCOLO", false);
 bitmapFormat.createQuotes("A Defesa da Autuação poderá ser entregue no protocolo da sede do " +
 "Detran-Pa, Postos de Atendimento ou remetido via postal para:", false);
 bitmapFormat.setNewLine(3);
 bitmapFormat.createQuotes("Departamento de Trânsito do Estado do Pará - Detran-PA", Paint.Align.CENTER, true, false);
 bitmapFormat.createQuotes("Av. Augusto Montenegro, KM 3, s/n, Mangueirão", Paint.Align.CENTER, true, false);
 bitmapFormat.createQuotes("CEP: 66640-000, Belém-PA", Paint.Align.CENTER, true, false);

 bitmapFormat.setNewLine(3);
 bitmapFormat.createQuotes("4- OBSERVAÇÕES", false);
 bitmapFormat.createQuotes("Em caso de não apresentação de DEFESA DE AUTUAÇÃO, do seu não " +
 "reconhecimento, ou ainda, de indeferimento, o DETRAN-PA aplicará a penalidade " +
 "expedindo a respectiva NOTIFICAÇÃO DE PENALIDADE;", false);
 bitmapFormat.setNewLine(3);
 bitmapFormat.createQuotes("É obrigatório a presença do código RENAINF ou INFRAEST na " +
 "notificação de penalidade, nos casos de infrações vinculadas ao veículo, sob " +
 "pena de invalidade da penalidade aplicada.", false);
 */
        bitmapFormat.setNewLine(25);
        bitmapFormat.printDocumnetClose();
        bitmapFormat.saveBitmap();
        return bitmapFormat.getPrintBitmap();
    }
}

