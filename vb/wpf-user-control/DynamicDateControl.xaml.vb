Public Class DynamicDateControl
    Inherits Viewbox

#Region " Constructors "

    Public Sub New()
        InitializeComponent()
        formatValue = "dd/MM"
        fontValue = "5x7"
        fontWidthValue = 5
        fontHeightValue = 7
        offsetValue = 0
        spacingValue = 0
        widthMultiplierValue = 1
        heightMultiplierValue = 1
    End Sub

#End Region

#Region " Properties "

    Public Property Content As Object
        Get
            Return labelContent.Content
        End Get
        Set(ByVal value As Object)
            labelContent.Content = value
        End Set
    End Property

    Public Property Foreground As Brush
        Get
            Return labelContent.Foreground
        End Get
        Set(ByVal value As Brush)
            labelContent.Foreground = value
        End Set
    End Property

    Public Property Type As String
        Get
            Return typeValue
        End Get
        Set(ByVal value As String)
            typeValue = value
        End Set
    End Property

    Public Property Format As String
        Get
            Return formatValue
        End Get
        Set(ByVal value As String)
            formatValue = value
            setWidth(fontWidthValue, spacingValue, widthMultiplierValue, formatValue.Count)
            setHeight(fontHeightValue, heightMultiplierValue)
        End Set
    End Property

    Public Property Font As String
        Get
            Return fontValue
        End Get
        Set(ByVal value As String)
            fontValue = value
            setWidth(fontWidthValue, spacingValue, widthMultiplierValue, formatValue.Count)
            setHeight(fontHeightValue, heightMultiplierValue)
        End Set
    End Property

    Public Property FontWidth As Integer
        Get
            Return fontWidthValue
        End Get
        Set(ByVal value As Integer)
            fontWidthValue = value
            setWidth(fontWidthValue, spacingValue, widthMultiplierValue, formatValue.Count)
            setHeight(fontHeightValue, heightMultiplierValue)
        End Set
    End Property

    Public Property FontHeight As Integer
        Get
            Return fontHeightValue
        End Get
        Set(ByVal value As Integer)
            fontHeightValue = value
            setWidth(fontWidthValue, spacingValue, widthMultiplierValue, formatValue.Count)
            setHeight(fontHeightValue, heightMultiplierValue)
        End Set
    End Property

    Public Property Offset As Integer
        Get
            Return offsetValue
        End Get
        Set(ByVal value As Integer)
            offsetValue = value
        End Set
    End Property

    Public Property Spacing As Integer
        Get
            Return spacingValue
        End Get
        Set(ByVal value As Integer)
            spacingValue = value
            setWidth(fontWidthValue, spacingValue, widthMultiplierValue, formatValue.Count)
            setHeight(fontHeightValue, heightMultiplierValue)
        End Set
    End Property

    Public Property WidthMultiplier As Integer
        Get
            Return widthMultiplierValue
        End Get
        Set(ByVal value As Integer)
            widthMultiplierValue = value
            setWidth(fontWidthValue, spacingValue, widthMultiplierValue, formatValue.Count)
            setHeight(fontHeightValue, heightMultiplierValue)
        End Set
    End Property

    Public Property HeightMultiplier As Integer
        Get
            Return heightMultiplierValue
        End Get
        Set(ByVal value As Integer)
            heightMultiplierValue = value
            setWidth(fontWidthValue, spacingValue, widthMultiplierValue, formatValue.Count)
            setHeight(fontHeightValue, heightMultiplierValue)
        End Set
    End Property

    Public Property Angle As Double
        Get
            Return CType(Me.LayoutTransform, RotateTransform).Angle
        End Get
        Set(ByVal value As Double)
            Me.LayoutTransform = New RotateTransform(value)
        End Set
    End Property

#End Region

#Region " Methods "

    Private Sub setWidth(ByVal fontWidth As Integer, ByVal spacing As Integer, ByVal widthMultiplier As Integer, ByVal charsCount As Integer)
        fontWidthValue = fontWidth
        spacingValue = spacing
        widthMultiplierValue = widthMultiplier
        Me.Width = ((fontWidth * widthMultiplier) * charsCount) + (spacing * (charsCount - 1))
    End Sub

    Private Sub setHeight(ByVal fontHeight As Integer, ByVal heightMultiplier As Integer)
        fontHeightValue = fontHeight
        heightMultiplierValue = heightMultiplier
        Me.Height = fontHeight * heightMultiplier
    End Sub

#End Region

#Region " Variables "

    Private typeValue As String
    Private formatValue As String
    Private fontValue As String
    Private fontWidthValue As Integer
    Private fontHeightValue As Integer
    Private offsetValue As Integer
    Private spacingValue As Integer
    Private widthMultiplierValue As Integer
    Private heightMultiplierValue As Integer

#End Region

End Class