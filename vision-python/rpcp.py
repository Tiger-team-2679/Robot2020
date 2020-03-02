PROTOCOL_MOST_VALUES = 4


def rpcp_protocol(values_list):
    data = ""
    for value in values_list:
        data = data + ' ' + str(value)
    data += "#"
    return data
