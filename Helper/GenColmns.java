import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GenColmns {

	private static String[] continuous = {"ADJINC", "AGEP", "SSP", "WKHP", "POVPIP"};
	private static String[] time = {"JWAP", "JWDP"};
	private static String[] weight = {"PWGTP", "pwgtp"};
	private static String[] incomes = {"OIP", "PAP", "RETP", "SEMP", "SSIP", "WAGP", "PERNP", "PINCP"};
	private static String[] years = {"CITWP", "MARHYP", "YOEP"};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String header = "RT,SERIALNO,SPORDER,PUMA,ST,ADJINC,PWGTP,AGEP,CIT,CITWP,COW,DDRS,DEAR,DEYE,DOUT,DPHY,DRAT,DRATX,DREM,ENG,FER,GCL,GCM,GCR,HINS1,HINS2,HINS3,HINS4,HINS5,HINS6,HINS7,INTP,JWMNP,JWRIP,JWTR,LANX,MAR,MARHD,MARHM,MARHT,MARHW,MARHYP,MIG,MIL,MLPA,MLPB,MLPC,MLPD,MLPE,MLPF,MLPG,MLPH,MLPI,MLPJ,MLPK,NWAB,NWAV,NWLA,NWLK,NWRE,OIP,PAP,RELP,RETP,SCH,SCHG,SCHL,SEMP,SEX,SSIP,SSP,WAGP,WKHP,WKL,WKW,WRK,YOEP,ANC,ANC1P,ANC2P,DECADE,DIS,DRIVESP,ESP,ESR,FOD1P,FOD2P,HICOV,HISP,INDP,JWAP,JWDP,LANP,MIGPUMA,MIGSP,MSP,NAICSP,NATIVITY,NOP,OC,OCCP,PAOC,PERNP,PINCP,POBP,POVPIP,POWPUMA,POWSP,PRIVCOV,PUBCOV,QTRBIR,RAC1P,RAC2P,RAC3P,RACAIAN,RACASN,RACBLK,RACNH,RACNUM,RACPI,RACSOR,RACWHT,RC,SCIENGP,SCIENGRLP,SFN,SFR,SOCP,SSPA,VPS,WAOB,FAGEP,FANCP,FCITP,FCITWP,FCOWP,FDDRSP,FDEARP,FDEYEP,FDISP,FDOUTP,FDPHYP,FDRATP,FDRATXP,FDREMP,FENGP,FESRP,FFERP,FFODP,FGCLP,FGCMP,FGCRP,FHINS1P,FHINS2P,FHINS3C,FHINS3P,FHINS4C,FHINS4P,FHINS5C,FHINS5P,FHINS6P,FHINS7P,FHISP,FINDP,FINTP,FJWDP,FJWMNP,FJWRIP,FJWTRP,FLANP,FLANXP,FMARHDP,FMARHMP,FMARHTP,FMARHWP,FMARHYP,FMARP,FMIGP,FMIGSP,FMILPP,FMILSP,FOCCP,FOIP,FPAP,FPERNP,FPINCP,FPOBP,FPOWSP,FPRIVCOVP,FPUBCOVP,FRACP,FRELP,FRETP,FSCHGP,FSCHLP,FSCHP,FSEMP,FSEXP,FSSIP,FSSP,FWAGP,FWKHP,FWKLP,FWKWP,FWRKP,FYOEP,pwgtp1,pwgtp2,pwgtp3,pwgtp4,pwgtp5,pwgtp6,pwgtp7,pwgtp8,pwgtp9,pwgtp10,pwgtp11,pwgtp12,pwgtp13,pwgtp14,pwgtp15,pwgtp16,pwgtp17,pwgtp18,pwgtp19,pwgtp20,pwgtp21,pwgtp22,pwgtp23,pwgtp24,pwgtp25,pwgtp26,pwgtp27,pwgtp28,pwgtp29,pwgtp30,pwgtp31,pwgtp32,pwgtp33,pwgtp34,pwgtp35,pwgtp36,pwgtp37,pwgtp38,pwgtp39,pwgtp40,pwgtp41,pwgtp42,pwgtp43,pwgtp44,pwgtp45,pwgtp46,pwgtp47,pwgtp48,pwgtp49,pwgtp50,pwgtp51,pwgtp52,pwgtp53,pwgtp54,pwgtp55,pwgtp56,pwgtp57,pwgtp58,pwgtp59,pwgtp60,pwgtp61,pwgtp62,pwgtp63,pwgtp64,pwgtp65,pwgtp66,pwgtp67,pwgtp68,pwgtp69,pwgtp70,pwgtp71,pwgtp72,pwgtp73,pwgtp74,pwgtp75,pwgtp76,pwgtp77,pwgtp78,pwgtp79,pwgtp80";
		String[] cols = header.split(",");
		System.out.println(cols.length);
		
		try {
			BufferedWriter write = new BufferedWriter(new FileWriter("E:\\DIC\\Code\\Helper\\data\\acs12_1yr_p.csv"));
			for(int i=0; i<cols.length; i++){
				write.write(i+","+cols[i]+","+getType(cols[i])+"\n");
			}
			write.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(cols.length);
		
	}

	private static String getType(String str){
		for(int i=0; i< continuous.length; i++){
			if(str.equals(continuous[i]))
				return "continuous";
		}
		for(int i=0; i< time.length; i++){
			if(str.equals(time[i]))
				return "time";
		}
		for(int i=0; i< incomes.length; i++){
			if(str.equals(incomes[i]))
				return "income";
		}
		for(int i=0; i< years.length; i++){
			if(str.equals(years[i]))
				return "years";
		}
		for(int i=0; i< weight.length; i++){
			if(str.startsWith(weight[i]))
				return "weight";
		}
		return "";
	}
}
