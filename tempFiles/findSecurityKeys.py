
import sys

def findCredentialKeys(dir):
	import os, fnmatch, re
	pattern = re.compile('[a-zA-Z0-9]{64}');
	os.chdir(dir)
	for dirpath, dirnames, files in os.walk(dir):
		for file in files:
			path = os.path.join(dirpath, file)
			if os.path.isfile(path):
				if path.endswith(('.java', '.txt', '.config','.csv')):
					file_text = open(path, "r").read()
					text = pattern.findall(file_text)
					if(text!=[]):
						return("true "+path)
						
	return "false"

d = sys.argv[1]
print findCredentialKeys(d)
   