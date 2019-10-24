package beauty;

public class Utilities {

	public Utilities(){}
	
	public double [] DistributionsQuadrants(double totalArea, double partialHeightWidthQ1, double partialHeightWidthQ2, double partialHeightWidthQ3, double partialHeightWidthQ4){
		
		double valuetoDistribute;
		
		
		
		double [] distributionsOriginals=new double[4];
		double [] distributions=new double[4];
		distributionsOriginals[0]=partialHeightWidthQ1;
		distributionsOriginals[1]=partialHeightWidthQ2;
		distributionsOriginals[2]=partialHeightWidthQ3;
		distributionsOriginals[3]=partialHeightWidthQ4;
		
		distributions[0]=partialHeightWidthQ1;
		distributions[1]=partialHeightWidthQ2;
		distributions[2]=partialHeightWidthQ3;
		distributions[3]=partialHeightWidthQ4;
		
		while(totalArea>0.1)
		{
			double min=100000000;
			double secondmin=100000000;
			double counterMins=0;
			for(int i=0;i<4;i++)
			{
				if (distributions[i]<min)
				{
					min=distributions[i];
				}
			}
			for(int i=0;i<4;i++)
			{
				if (distributions[i]<secondmin && distributions[i]>min)
				{
					secondmin=distributions[i];
				}
			}
			
			for(int i=0;i<4;i++)
			{
				
				if (distributions[i]==min)
				{
					counterMins=counterMins+1;
					
				}
			}
			
			if((totalArea/counterMins)> secondmin && secondmin!=100000000)
			{
				for(int i=0;i<4;i++)
				{
					if(distributions[i]==min)
					{
						double toSubstract=secondmin-distributions[i];
						distributions[i]=distributions[i]+(toSubstract);
						totalArea=totalArea-toSubstract;
					}
				}	
				
			}
			else
			{
				valuetoDistribute=totalArea/counterMins;
				for(int i=0;i<4;i++)
				{
					if(distributions[i]==min)
					{
						distributions[i]=distributions[i]+valuetoDistribute;
						totalArea=totalArea-valuetoDistribute;
					}
				}
			}
			
			
			
		}
		for(int i=0;i<4;i++)
		{
			distributionsOriginals[i]=(distributions[i]-distributionsOriginals[i]);
		}
		
		return distributionsOriginals;
	} 
	
	public double bestXYSummatory(double [] partialXY)
	{
		double max=0;
		for(int i=0;i<4;i++)
		{
			if(partialXY[i]>max)
			{
				max=partialXY[i];
			}
		}
		return max;
	}
	public double sortXYSummatory(double [] partialXY)
	{
		double max=0;
		for(int i=0;i<4;i++)
		{
			if(partialXY[i]>max)
			{
				max=partialXY[i];
			}
		}
		return max;
	}
}
