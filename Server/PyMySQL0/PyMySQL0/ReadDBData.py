def getDBdata():
    fname = "DBlogin.txt"

    f = open(fname, 'r')

    lines = f.readlines()
    keys = []
    f.close()

    for i in lines:
        a = i.split()
        keys.append(a[2])

    return keys

def getInputdata():
    fname = "InputData.txt"

    with open(fname, 'r') as f:
        lines = f.read()
        keys = lines.split('\n')

    return keys