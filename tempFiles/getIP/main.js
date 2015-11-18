var AWS = require('aws-sdk');
var fs = require('fs');

AWS.config.update({accessKeyId: '', secretAccessKey: ''});  // Put AWS account access key id and secret access key
  
AWS.config.update({region: 'us-east-1'});

var ec2 = new AWS.EC2();

var params = {
  DryRun: false,
  Filters: [
    {
      Name: 'image-id',
      Values: [
        'ami-1c552a76',
        /* more items */
      ],
    },
    {
      Name: 'instance-state-code',
      Values: [
        '16',
        /* more items */
      ],
    }
    /* more items */
  ],
  MaxResults: 5,
};
ec2.describeInstances(params, function(err, data) {
  if (err) console.log(err, err.stack); // an error occurred
  else     console.log(data.Reservations[0].Instances[0].PublicDnsName);
});
