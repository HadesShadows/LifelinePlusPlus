pragma solidity ^0.6.0;

contract Donor {

    mapping(uint256 => donor) donorlist;

    struct donor {
        string donor_name;
        string donor_address;
        string donor_age;
    }
    donor d;

    address owner;

      constructor() public {
          owner = msg.sender;
      }
      
      
      // modifier to give access only to hospital
      modifier isOwner() {

         require(msg.sender == owner, "Access is not allowed");

         _;

     }

    uint256 donor_id=1;

    function store_donor_details(string memory _donor_name, string memory _donor_address, string memory _donor_age) public isOwner{
        d.donor_name = _donor_name;
        d.donor_address = _donor_address;
        d.donor_age = _donor_age;
        donorlist[donor_id] = d;
        donor_id++;
    }

    function retreive_donor_details(uint256 donor_ids) public view returns (string memory, string memory, string memory)
    {
        donor memory ds = donorlist[donor_ids];
        return (ds.donor_name, ds.donor_address, ds.donor_age);
    }

    function return_donor_count() public view returns (uint256)
    {
        return donor_id-1;
    }
}