# README #

Traveler Information Message Documentation from the j2735 Surface Vehicle Standard

MSG_TravelerInformation Message  (TIM) 

The Traveler Information message is used to send various types of information  (advisory and road sign types) to equipped devices. It makes heavy use of the ITIS encoding system to send well known phrases, but allows limited text for local place names. The supported message types specify several sub-dialects of ITIS phrase patterns to further reduce the number of octets to be sent. The expressed messages are active at a precise start and duration period, which can be specified to a resolution of a minute. The affected local area can be expressed using either a radius system or one of the systems of short defined regions, similar to the way roadway geometry is defined in the MAP messages. 

ASN.1 Representation:   

TravelerInformation ::= SEQUENCE {    
	msgCnt MsgCount,      
	timeStamp MinuteOfTheYear OPTIONAL,    
	packetID UniqueMSGID OPTIONAL,   
	urlB URL-Base OPTIONAL,   
 	-- A set of one or more self contained     
 	-- traveler information messages (frames)    
 	dataFrames  TravelerDataFrameList,   
    regional    SEQUENCE (SIZE(1..4)) OF 
                RegionalExtension {{REGION.Reg-TravelerInformation}} OPTIONAL,    
	...    
	}

Data Frame: DF_TravelerDataFrame 

Use:  The DF_TravelerDataFrame is used to send a single "message" in a TIM message. The data frame allows sending various advisory and road sign types of information to equipped devices. It uses the ITIS encoding system to send well- known phrases, but allows limited text for local place names. The supported message types specify several sub-dialects of ITIS phrase patterns to further reduce the number of octets to be sent. The expressed messages are active at a precise start and duration period, which can be specified to a resolution of a minute. The affected local area (or set of areas) can be expressed using either a radius system or one of the two systems of short defined regions. This expression is similar to the way roadway geometry is defined in the map fragment messages.  The ability to send this message is controlled by the SSPIndex which links back to the sender’s CERT.

ASN.1 Representation:   

TravelerDataFrame ::= SEQUENCE {    
	-- Part I, Frame header    
	sspTimRights    SSPindex,      
	frameType   TravelerInfoType,  -- (enum, advisory or road sign)    
	msgId CHOICE {          
		furtherInfoID   FurtherInfoID, -- links to ATIS msg          
		roadSignID      RoadSignID     -- an ID to other data         
		},
	startYear   DYear OPTIONAL,  -- only if needed    
	startTime   MinuteOfTheYear,    
	duratonTime MinutesDuration,    
	priority    SignPrority,       

	-- Part II, Applicable Regions of Use    
	sspLocationRights  SSPindex,      
	regions SEQUENCE (SIZE(1..16)) OF GeographicalPath, 
 
   -- Part III, Content    
   sspMsgRights1    SSPindex,  -- allowed message types    
   sspMsgRights2    SSPindex,  -- allowed message content    
   content  CHOICE {             
   		advisory     ITIS.ITIScodesAndText,                                 -- typical ITIS warnings
   		             workZone     WorkZone,                                 -- work zone signs and directions             
   		             genericSign  GenericSignage,                           -- MUTCD signs and directions             
   		             speedLimit   SpeedLimit,                               -- speed limits and cautions             
   		             exitService  ExitService                               -- roadside avaiable services             
   		             -- other types may be added in future revisions             
   		             },       
 	url     URL-Short OPTIONAL,  -- May link to image or other content    
 	...
 	}    