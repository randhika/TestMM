// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class NaviSearchData
    implements Serializable, Cloneable
{
    public static class Edge
        implements Serializable
    {

        private static final long serialVersionUID = 0xed5768779dfc0098L;
        public String AirportDPLinkSP;
        public String AirportDPLinkSPDisp;
        public String AirportTicketLinkSP;
        public ArrayList RidingPosition;
        public ArrayList StopStations;
        public String arrivalDatetime;
        public String arrivalTrackNumber;
        public int attentionId;
        public String attentionText;
        public ArrayList carType;
        public int color;
        public String departureDatetime;
        public String departureTrackNumber;
        public int distance;
        public int edgeid;
        public int fromState;
        public String goalPointTarget;
        public int passStCount;
        public int price;
        public int priceTo;
        public String railDispName;
        public String railname;
        public String startPointTarget;
        public int time;
        public String timeType;
        public int toState;
        public int traffic;
        public String trainId;

        public Edge()
        {
            edgeid = 0;
            passStCount = 0;
            time = 0;
            traffic = 1;
            fromState = 0;
            toState = 0;
            color = 0;
            distance = 0;
            StopStations = null;
            RidingPosition = null;
            carType = null;
        }
    }

    public static class NaviPointData
        implements Serializable
    {

        private static final long serialVersionUID = 0xa0674391d24d9c7dL;
        public int areaCode;
        public String lat;
        public String lon;
        public String nearStLat;
        public String nearStLon;
        public int position;
        public String shortAddress;
        public String stationCode;
        public String stationName;
        public String target;
        public int type;

        public NaviPointData()
        {
        }
    }

    public static class NaviRouteData
        implements Serializable
    {

        private static final long serialVersionUID = 0x2eefba9f82ab1a04L;
        public String Teiki1;
        public String Teiki3;
        public String Teiki6;
        public boolean cheap;
        public int distance;
        public boolean easy;
        public ArrayList edgeExpPrice;
        public ArrayList edgePrice;
        public ArrayList edges;
        public String farePrice;
        public boolean fast;
        public String goalTime;
        public boolean previousTaxFareTeiki1;
        public boolean previousTaxFareTeiki3;
        public boolean previousTaxFareTeiki6;
        public String startTime;
        public ArrayList teikiDetials;
        public String totalExpPrice;
        public boolean totalPreviousTaxFare;
        public String totalPrice;
        public int totaldistance;
        public int totaltime;
        public int transfercount;
        public ArrayList types;

        public NaviRouteData()
        {
            totaltime = 0;
            totaldistance = 0;
            teikiDetials = null;
            cheap = false;
            easy = false;
            fast = false;
            distance = 0;
            transfercount = 0;
            types = null;
            edges = null;
        }
    }

    public static class Price
        implements Serializable
    {

        private static final long serialVersionUID = 0x4e717ee0ed55f81fL;
        public int edgeFrom;
        public int edgeTo;
        public boolean previousTaxFare;
        public String ticketType;
        public String type;
        public String value;

        public Price()
        {
        }
    }

    public static class RidingPosition
        implements Serializable
    {

        private static final long serialVersionUID = 0x6ee276040ed45a73L;
        public ArrayList Cars;
        public String direction;
        public int isFrontFirstCar;

        public RidingPosition()
        {
        }
    }

    public static class RidingPositionCar
        implements Serializable
    {

        private static final long serialVersionUID = 0xcd3b1930e1e437b6L;
        public String allOutflowsText;
        public String numOfCar;
        public ArrayList outflow;

        public RidingPositionCar()
        {
        }
    }

    public static class RidingPositionOutflow
        implements Serializable
    {

        private static final long serialVersionUID = 0xfd0d6058cbd0fbd0L;
        public String Means;
        public String carNo;

        public RidingPositionOutflow()
        {
        }
    }

    public static class StopStation
        implements Serializable
    {

        private static final long serialVersionUID = 0xd5aedf077dd07561L;
        public String arraivalTime;
        public Long arrivalUnixTimestamp;
        public String code;
        public String departureTime;
        public Long departureUnixTimestamp;
        public String name;

        public StopStation()
        {
        }
    }

    public static class TeikiDetail
        implements Serializable
    {

        private static final long serialVersionUID = 0x311f13c9c4df4427L;
        public int edgeFrom;
        public int edgeTo;
        public boolean previous;
        public String teiki1;
        public String teiki3;
        public String teiki6;

        public TeikiDetail()
        {
            edgeFrom = 0;
            edgeTo = 0;
            previous = false;
            teiki1 = "";
            teiki3 = "";
            teiki6 = "";
        }
    }


    private static final long serialVersionUID = 0xea46a9d76b90967cL;
    public int errorId;
    public String errorText;
    public NaviPointData goalHistory;
    public ArrayList goalStationList;
    public HashMap points;
    public ArrayList routes;
    public NaviPointData startHistory;
    public ArrayList startStationList;
    public String url;
    public NaviPointData viaHistory;
    public ArrayList viaStationList;
    public String webUrl;

    public NaviSearchData()
    {
    }

    public Object clone()
    {
        Object obj;
        try
        {
            obj = super.clone();
        }
        catch (CloneNotSupportedException clonenotsupportedexception)
        {
            throw new InternalError(clonenotsupportedexception.toString());
        }
        return obj;
    }
}
