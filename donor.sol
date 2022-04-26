pragma solidity ^0.6.0;

contract Test {

    mapping(uint256 => donor) donorlist;

    struct donor {
        string donor_name;
        string donor_address;
        string donor_age;
        string donor_bg_grp; 
        address useradd;
        uint256 flag;
        string giver_name;
        string giver_address;
        string giver_age;
    }
    donor d;

    address owner;
      constructor() public {
          owner = msg.sender;
      }
      
      
      //modifier to give access only to hospital
      modifier isOwner() {

         require(msg.sender == owner, "Access is not allowed");

         _;

     }

    uint256 donor_id=1;

    function isDuplicate() public view returns (bool) {
        for(uint256 i=1 ;i< donor_id;i++)
        {
            donor memory ds = donorlist[i];
            if(ds.useradd == msg.sender)
            {
                return true;
            }
        }
        return false;
    }

    function store_donor_details(string memory _donor_name, string memory _donor_address, string memory _donor_age , string memory _donor_bg_grp) public
    {
        if(!isDuplicate())
        {
            d.donor_name = _donor_name;
            d.donor_address = _donor_address;
            d.donor_age = _donor_age;
            d.donor_bg_grp = _donor_bg_grp;
            d.useradd = msg.sender;
            d.flag=0;
            d.giver_name = '0';
            d.giver_address= '0';
            d.giver_age= '0';
            donorlist[donor_id] = d;
            donor_id++;
        }
    }

    function retreive_donor_details(uint256 donor_ids) public view isOwner returns (string memory, string memory, string memory, string memory)
    {
        donor memory ds = donorlist[donor_ids];
        return (ds.donor_name, ds.donor_address, ds.donor_age, ds.donor_bg_grp);
    }

    function return_donor_count() public view returns (uint256)
    {
        if((donor_id-1)<0)
        {
            return 0;
        }
        else
        {
            return donor_id-1;
        }
    }

    function return_bg_data() public view returns (uint256[8] memory)
    {
        uint256[8] memory bg_grp;    
        for(uint256 i=0;i<8;i++)
        {
            bg_grp[i]=0;
        }
        for(uint256 i=1 ;i< donor_id;i++)
        {
            donor memory ds = donorlist[i];
            if(keccak256(abi.encodePacked(ds.donor_bg_grp)) == keccak256(abi.encodePacked('O+')))
            {
                bg_grp[0]++;
            }
            else if(keccak256(abi.encodePacked(ds.donor_bg_grp)) == keccak256(abi.encodePacked("O-")))
            {
                bg_grp[1]++;
            }
            else if(keccak256(abi.encodePacked(ds.donor_bg_grp)) == keccak256(abi.encodePacked("A+")))
            {
                bg_grp[2]++;
            }
            else if(keccak256(abi.encodePacked(ds.donor_bg_grp)) == keccak256(abi.encodePacked("A-")))
            {
                bg_grp[3]++;
            }
            else if(keccak256(abi.encodePacked(ds.donor_bg_grp)) == keccak256(abi.encodePacked("B+")))
            {
                bg_grp[4]++;
            }
            else if(keccak256(abi.encodePacked(ds.donor_bg_grp)) == keccak256(abi.encodePacked("B-")))
            {
                bg_grp[5]++;
            }
            else if(keccak256(abi.encodePacked(ds.donor_bg_grp)) == keccak256(abi.encodePacked("AB+")))
            {
                bg_grp[6]++;
            }
            else if(keccak256(abi.encodePacked(ds.donor_bg_grp)) == keccak256(abi.encodePacked("AB-")))
            {
                bg_grp[7]++;
            }
        }
        return bg_grp;
    }

    function request_blood() public 
    {
        for(uint256 i=1;i<donor_id;i++)
        {
            donor memory ds = donorlist[i];
            donor memory temp = ds;
            if(temp.useradd == msg.sender)
            {
                temp.flag=1;
                donorlist[i]=temp;
                break;
            }
        }
    }

    function return_request_count() public view returns (uint256)
    {
        uint256 count = 0;
        for(uint256 i=1;i<donor_id;i++)
        {
            donor memory ds = donorlist[i];
            if(ds.flag == 1)
            {
                count++;
            }
        }
        return count;
    }

    function return_request_details() public view returns (string memory, string memory, string memory, string memory)
    {
        donor memory ds = donorlist[1];
        string memory bg;
        for(uint256 i=1;i<donor_id;i++)
        {
            ds = donorlist[i];
            if(ds.useradd == msg.sender)
            {
                bg = ds.donor_bg_grp;
                break;
            }
        }
        for(uint256 i=1;i<donor_id;i++)
        {
            donor memory d = donorlist[i];
            if(d.useradd == ds.useradd)
            {
                continue;
            }
            if(keccak256(abi.encodePacked(bg)) == keccak256(abi.encodePacked(d.donor_bg_grp)) && d.flag==1)
            {
                return (d.donor_name, d.donor_address, d.donor_age, d.donor_bg_grp);
            }
        }
        return ("0","0","0","0");
    }

    function donate_blood() public 
    {
        donor memory d = donorlist[1];
        uint256 i = 1;
        for(i=1;i<donor_id;i++)
        {
            d= donorlist[i];
            if(d.useradd == msg.sender)
            {
                break;
            } 
        }
        for(uint256 j=1;j<donor_id;j++)
        {
            donor memory temp = donorlist[j];
            donor memory temps = temp;
            if(i==j)
            {
                continue;
            }
            if(keccak256(abi.encodePacked(d.donor_bg_grp)) == keccak256(abi.encodePacked(temp.donor_bg_grp)))
            {
                temps.flag=0;
                temps.giver_name=d.donor_name;
                temps.giver_address=d.donor_address;
                temps.giver_age=d.donor_age;
                donorlist[j]=temps;
                break;
            }
        }
    }

    function return_giver_details() public view returns (string memory, string memory, string memory)
    {
        donor memory ds = donorlist[1];
        uint256 i = 1;
        for(i=1;i<donor_id;i++)
        {
            ds= donorlist[i];
            if(ds.useradd == msg.sender)
            {
                break;
            } 
        }
        return (ds.giver_name, ds.giver_address, ds.giver_age);
    }
}