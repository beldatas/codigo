package net.sistran.mobile.placa.dados;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;

import net.sistran.mobile.appconstantes.AppConstants;
import net.sistran.mobile.appobjeto.AppObject;
import net.sistran.mobile.database.DatabaseCreator;
import net.sistran.R;

public class PlacaViewFormat {

    private SpannableString resultViewData;

    private String plate, ufPlaca, municipio, marca, modelo, chassi, color, type, especie, category, licenseYear, licenseDate,
            licenseStatus, ipva, insurance, infractions, restrictions, dateStatus, numeroMotor, numeroLacre,
            impedimento, furto;
    private boolean isVibrateOrRinging;
    private Context context;

    public PlacaViewFormat(DataFromPlate dataPlate, Context context) {
        this.context = context;

        this.plate = context.getResources().getString(R.string.placa_format)
                + dataPlate.getPlate() + "  ";

        this.ufPlaca = AppConstants.NEW_LINE
                + context.getResources().getString(R.string.uf_format)
                + dataPlate.getUf();

        this.municipio = AppConstants.NEW_LINE
                + context.getResources().getString(R.string.municipio_format)
                + dataPlate.getMunicipio();

        this.chassi = AppConstants.NEW_LINE
                + context.getResources().getString(R.string.chassi_format)
                + dataPlate.getChassi();

        this.marca = AppConstants.NEW_LINE
                + context.getResources().getString(R.string.marca_format)
                + dataPlate.getMarca();

        this.modelo = AppConstants.NEW_LINE
                + context.getResources().getString(R.string.modelo_format)
                + dataPlate.getModel() + AppConstants.NEW_LINE;

        this.color = context.getResources().getString(R.string.cor_format)
                + dataPlate.getColor() + AppConstants.NEW_LINE;

        this.type = context.getResources().getString(R.string.tipo_format)
                + dataPlate.getType() + AppConstants.NEW_LINE;

        this.especie = context.getResources().getString(R.string.especie_format)
                + dataPlate.getEspecie() + AppConstants.NEW_LINE;

        this.category = context.getResources().getString(R.string.categoria_format)
                + dataPlate.getCategory() + AppConstants.NEW_LINE;

        this.licenseYear = context.getResources().getString(R.string.ano_licenciamento_format)
                + dataPlate.getLicenceYear() + AppConstants.NEW_LINE;

        this.licenseDate = context.getResources().getString(
                R.string.data_licenciamento_format)
                + dataPlate.getLicenceData() + AppConstants.NEW_LINE;

        this.licenseStatus = dataPlate.getLicenceStatus();

        this.numeroLacre = context.getResources().getString(R.string.lacre_format)
                + dataPlate.getNumeroLacre() + AppConstants.NEW_LINE;

        this.numeroMotor = context.getResources().getString(R.string.motor_format)
                + dataPlate.getNumeroMotor() + AppConstants.TWO_LINES;

        this.impedimento = context.getResources().getString(R.string.impedimento_format)
                + dataPlate.getRegistroImpedimento() + AppConstants.NEW_LINE;

        this.furto = context.getResources().getString(
                R.string.furto_format)
                + dataPlate.getRegistroFurto() + AppConstants.NEW_LINE;

        /*this.infractions = context.getResources().getString(
                R.string.multas_format)
                + dataPlate.getInfractions() + AppConstants.NEW_LINE;

        this.restrictions = context.getResources().getString(
                R.string.restricoes_format)
                + dataPlate.getRestrictions() + AppConstants.NEW_LINE;*/

        this.dateStatus = context.getResources().getString(R.string.date_format)
                + dataPlate.getDate() + AppConstants.NEW_LINE;

        setResultViewData(dataPlate);
    }

    public boolean isVibrateOrRinging() {
        return isVibrateOrRinging;
    }

    private void setVibrateOrRinging(boolean isVibrateOrRinging) {
        this.isVibrateOrRinging = isVibrateOrRinging;
    }

    public SpannableString getResultViewData() {
        return resultViewData;
    }

    private void setResultViewData(DataFromPlate dataPlate) {

        String result = plate + licenseStatus + chassi + ufPlaca + municipio + marca
                + modelo + color + type + especie + category
                + licenseYear + licenseDate + impedimento + furto + numeroLacre
                + numeroMotor + dateStatus;

        resultViewData = new SpannableString(result);

        if (dataPlate.getLicenceStatus() != null) {
            int position = result.indexOf(dataPlate.getLicenceStatus());

            if ((dataPlate.getLicenceStatus()).equals("Normal")
                    || (dataPlate.getLicenceStatus()).equals("normal")) {
                resultViewData.setSpan(new ForegroundColorSpan(Color.BLUE),
                        position, (position + (dataPlate
                                .getLicenceStatus()).length()), 0);
                setVibrateOrRinging(false);
            } else {
                setVibrateOrRinging(true);
                resultViewData.setSpan(new ForegroundColorSpan(Color.RED),
                        position, (position + (dataPlate
                                .getLicenceStatus()).length()), 0);
            }

            if (position != -1) {
                resultViewData.setSpan(new RelativeSizeSpan(1.5f), position,
                        (position + (dataPlate.getLicenceStatus())
                                .length()), 0);
            }

        }

        ////
        /*
        if (dataPlate.getRegistroFurto() != null) {
            int position = result.indexOf(dataPlate.getRegistroFurto());

            if ((dataPlate.getRegistroFurto()).equals("Não")
                    || (dataPlate.getRegistroFurto()).equals("não")) {
                resultViewData.setSpan(new ForegroundColorSpan(Color.BLUE),
                        position, (position + (dataPlate
                                .getRegistroFurto()).length()), 0);
                setVibrateOrRinging(false);
            } else {
                setVibrateOrRinging(true);
                resultViewData.setSpan(new ForegroundColorSpan(Color.RED),
                        position, (position + (dataPlate
                                .getRegistroFurto()).length()), 0);

                    if (position != -1) {
                        resultViewData.setSpan(new RelativeSizeSpan(1.5f), position,
                                (position + (dataPlate.getRegistroFurto())
                                        .length()), 0);
                    }
            }

        }
        */

    }

    public void setWarning() {
        if (isVibrateOrRinging) {
            if (DatabaseCreator.getDatabaseAdapterSetting(context)
                    .getVibrator()) {
                AppObject.startVibrate(context);
            }
            if (DatabaseCreator.getDatabaseAdapterSetting(context)
                    .getRingtone()) {
                AppObject.startWarning(context);
            }
        }
    }

}
